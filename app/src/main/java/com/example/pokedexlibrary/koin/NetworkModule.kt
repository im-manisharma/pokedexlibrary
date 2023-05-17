package com.example.pokedexlibrary.koin

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.pokedexlibrary.ApiService
import com.example.pokedexlibrary.R
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder().addInterceptor(logging)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS)
            .addInterceptor(ConnectivityInterceptor(androidContext().applicationContext))
            .addInterceptor(RequestInterceptor())
            .build()
    }

    single {
        val gsonConverterFactory = GsonConverterFactory.create(GsonBuilder().setLenient().create())

        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(get())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(ApiService::class.java)
    }
}

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline(context)) throw NoConnectivityException(context)
        return chain.proceed(chain.request())
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityManager.activeNetwork?.run {
            connectivityManager.getNetworkCapabilities(this)?.run {
                return when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } ?: false
        } ?: false
    }
}

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("osVersion", Build.VERSION.SDK_INT.toString())
            .build()
        return chain.proceed(request)
    }
}

class NoConnectivityException(context: Context) : IOException() {
    override val message = context.getString(R.string.no_internet_connection)
}