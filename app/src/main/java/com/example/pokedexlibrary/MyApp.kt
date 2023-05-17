package com.example.pokedexlibrary

import android.app.Application
import com.example.pokedexlibrary.koin.myViewModel
import com.example.pokedexlibrary.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(networkModule, myViewModel))
        }
    }
}