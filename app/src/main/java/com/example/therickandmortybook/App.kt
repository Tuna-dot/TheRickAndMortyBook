package com.example.therickandmortybook

import android.app.Application
import com.example.therickandmortybook.di.networkModule
import com.example.therickandmortybook.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, viewModel)
        }
    }
}