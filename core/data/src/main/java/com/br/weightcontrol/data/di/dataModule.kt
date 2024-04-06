package com.br.weightcontrol.data.di

import br.com.weightcontrol.datastore.di.dataStoreModule
import com.br.weightcontrol.data.repository.GoalRepository
import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.data.repository.TrackRepository
import com.br.weightcontrol.data.repository.impl.GoalRepositoryImpl
import com.br.weightcontrol.data.repository.impl.UserRepositoryImpl
import com.br.weightcontrol.data.repository.impl.TrackRepositoryImpl
import com.br.weightcontrol.di.databaseModule
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single<TrackRepository> { TrackRepositoryImpl(get()) }
    single<GoalRepository> { GoalRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}
