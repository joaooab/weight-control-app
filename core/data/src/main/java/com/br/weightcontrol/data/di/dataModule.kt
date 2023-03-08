package com.br.weightcontrol.data.di

import com.br.weightcontrol.data.repository.GoalRepository
import com.br.weightcontrol.data.repository.TrackRepository
import com.br.weightcontrol.data.repository.impl.GoalRepositoryImpl
import com.br.weightcontrol.data.repository.impl.TrackRepositoryImpl
import com.br.weightcontrol.di.databaseModule
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule)

    single<TrackRepository> { TrackRepositoryImpl(get()) }
    single<GoalRepository> { GoalRepositoryImpl(get()) }
}
