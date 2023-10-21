package com.br.weightcontrol.settings.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.model.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SettingsViewModel(userRepository: UserRepository) : ViewModel() {

    val user = userRepository.userData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = User(),
    )
}