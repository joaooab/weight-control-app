package com.br.weightcontrol.domain.usecase.di

import com.br.weightcontrol.domain.usecase.AddTrackUseCase
import com.br.weightcontrol.domain.usecase.impl.AddTrackUseCaseImpl
import com.br.weightcontrol.domain.usecase.session.SessionManager
import com.br.weightcontrol.domain.usecase.session.impl.SessionManagerImpl
import org.koin.dsl.module

val domainModule = module {
    single<SessionManager> { SessionManagerImpl(get()) }

    factory<AddTrackUseCase> { AddTrackUseCaseImpl(get(), get()) }
}