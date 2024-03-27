package com.br.weightcontrol.settings.navigation.di

import com.br.weightcontrol.settings.navigation.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::SettingsViewModel)
}