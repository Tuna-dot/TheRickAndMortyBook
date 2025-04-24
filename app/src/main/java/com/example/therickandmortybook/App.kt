package com.example.therickandmortybook

import android.app.Application
import com.example.therickandmortybook.data.serviceLocator.dispatcherModule
import com.example.therickandmortybook.data.serviceLocator.localeModule
import com.example.therickandmortybook.data.serviceLocator.networkModule
import com.example.therickandmortybook.ui.serviceLocator.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, localeModule,viewModelModule, dispatcherModule)
        }
    }
}