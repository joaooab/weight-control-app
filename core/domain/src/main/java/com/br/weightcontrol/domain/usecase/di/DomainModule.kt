package com.br.weightcontrol.domain.usecase.di

import com.br.weightcontrol.domain.usecase.session.SessionManager
import com.br.weightcontrol.domain.usecase.session.impl.SessionManagerImpl
import com.br.weightcontrol.domain.usecase.usecase.InitSessionUseCase
import com.br.weightcontrol.domain.usecase.usecase.impl.InitSessionUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<SessionManager> { SessionManagerImpl(get()) }
    factory<InitSessionUseCase> { InitSessionUseCaseImpl(get()) }
}