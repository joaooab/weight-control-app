package com.br.weightcontrol.di

import com.br.weightcontrol.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val daoModule = module {
    single { AppDatabase.getInstance(androidContext()) }
    single { get<AppDatabase>().weightDao() }
    single { get<AppDatabase>().goalDao() }
}
