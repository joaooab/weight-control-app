package com.br.weightcontrol.di

import com.br.weightcontrol.data.di.dataModule
import org.koin.dsl.module

val weiAppModule = module {
    includes(
        dataModule,
    )
}
