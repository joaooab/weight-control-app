package com.br.weightcontrol.di

import com.br.weightcontrol.data.di.dataModule
import com.br.weightcontrol.history.di.historyModule
import com.br.weightcontrol.home.di.homeModule
import com.br.weightcontrol.track.di.trackModule
import org.koin.dsl.module

val weiAppModule = module {
    includes(
        dataModule,
        homeModule,
        trackModule,
        historyModule,
    )
}
