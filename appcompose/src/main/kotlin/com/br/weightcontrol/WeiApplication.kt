package com.br.weightcontrol

import android.app.Application
import com.br.weightcontrol.di.weiAppModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeiApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WeiApplication)
            modules(weiAppModule)
        }
    }
}
