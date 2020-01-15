package com.br.weightcontrol.di

import com.br.weightcontrol.repository.WeightRepository
import com.br.weightcontrol.repository.WeightRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WeightRepository> { WeightRepositoryImpl(get()) }
}