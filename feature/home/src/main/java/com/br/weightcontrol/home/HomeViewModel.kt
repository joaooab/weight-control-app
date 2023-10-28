package com.br.weightcontrol.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.GoalRepository
import com.br.weightcontrol.data.repository.TrackRepository
import com.br.weightcontrol.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    trackRepository: TrackRepository,
    goalRepository: GoalRepository,
    userRepository: UserRepository,
) : ViewModel() {

    val progressState: StateFlow<Progress> = combine(
        trackRepository.getLastStream(),
        trackRepository.getPreviewsStream(),
        trackRepository.getLowerStream(),
        trackRepository.getHigherStream(),
    ) { last, first, lower, higher ->
        Progress(last, first, lower, higher)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Progress(),
    )

    val user = userRepository.stream.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val goalState = goalRepository.getLastStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null,
    )

    val historyState = trackRepository.getAllStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = listOf(),
    )
}
