package com.br.weightcontrol

import android.app.Application
import com.br.weightcontrol.di.daoModule
import com.br.weightcontrol.di.repositoryModule
import com.br.weightcontrol.di.utilModule
import com.br.weightcontrol.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(daoModule, repositoryModule, viewModelModule, utilModule))
        }
    }
}