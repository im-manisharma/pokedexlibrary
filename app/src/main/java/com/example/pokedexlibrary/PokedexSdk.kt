package com.example.pokedexlibrary

import android.app.Application
import com.example.pokedexlibrary.koin.myViewModel
import com.example.pokedexlibrary.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

object PokedexSdk {
    fun initialize(context: Application){
        startKoin {
            androidLogger()
            androidContext(context)
            modules(listOf(networkModule, myViewModel))
        }
    }
}