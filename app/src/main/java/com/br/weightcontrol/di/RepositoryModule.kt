package com.br.weightcontrol.di

import com.br.weightcontrol.data.goal.GoalRepository
import com.br.weightcontrol.data.goal.GoalRepositoryImpl
import com.br.weightcontrol.data.weight.WeightRepository
import com.br.weightcontrol.data.weight.WeightRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WeightRepository> { WeightRepositoryImpl(get()) }
    single<GoalRepository> { GoalRepositoryImpl(get()) }
}