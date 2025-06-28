package com.reyaz.growwstocks

import android.app.Application
import com.reyaz.core.database.di.databaseModule
import com.reyaz.core.network.di.networkModule
import com.reyaz.feature.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(databaseModule, homeModule, networkModule)
        }
    }
}