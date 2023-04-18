package com.br.weightcontrol.track.di

import com.br.weightcontrol.track.TrackViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val trackModule = module {
    viewModelOf(::TrackViewModel)
}