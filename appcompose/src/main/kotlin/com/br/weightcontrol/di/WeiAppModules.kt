package com.br.weightcontrol.di

import br.com.weightcontrol.profile.di.profileModule
import com.br.weightcontrol.MainViewModel
import com.br.weightcontrol.data.di.dataModule
import com.br.weightcontrol.domain.usecase.di.domainModule
import com.br.weightcontrol.history.di.historyModule
import com.br.weightcontrol.home.di.homeModule
import com.br.weightcontrol.track.di.trackModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val weiAppModule = module {
    viewModelOf(::MainViewModel)

    includes(
        dataModule,
        domainModule,
        homeModule,
        trackModule,
        historyModule,
        profileModule
    )
}
