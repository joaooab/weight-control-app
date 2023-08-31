package br.com.weightcontrol.profile.di

import androidx.lifecycle.SavedStateHandle
import br.com.weightcontrol.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel { (handle: SavedStateHandle) -> ProfileViewModel(handle, get()) }
}