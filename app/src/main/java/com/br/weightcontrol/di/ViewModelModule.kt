package com.br.weightcontrol.di

import com.br.weightcontrol.ui.dashboard.DashboardViewModel
import com.br.weightcontrol.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DashboardViewModel(get(), get()) }
}