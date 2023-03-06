package com.br.weightcontrol.di

import org.koin.dsl.module

val weiAppModule = module {
    includes(
        databaseModule,
    )
}