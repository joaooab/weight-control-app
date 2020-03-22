package com.br.weightcontrol.di

import com.br.weightcontrol.util.PreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilModule = module {
    single { PreferencesHelper(androidContext()) }
}