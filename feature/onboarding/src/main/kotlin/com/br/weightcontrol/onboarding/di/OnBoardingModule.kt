package com.br.weightcontrol.onboarding.di

import com.br.weightcontrol.onboarding.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val onBoardingModule = module {
    viewModelOf(::OnBoardingViewModel)
}