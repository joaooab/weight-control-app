package br.com.weightcontrol.di

import GoalViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val goalModule = module {
    viewModelOf(::GoalViewModel)
}