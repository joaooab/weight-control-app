package com.br.weightcontrol.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.GoalRepository
import com.br.weightcontrol.data.repository.TrackRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(repository: TrackRepository, goalRepository: GoalRepository) : ViewModel() {

    val progressState: StateFlow<Progress> = combine(
        repository.getLastStream(),
        repository.getPreviewsStream(),
        repository.getLowerStream(),
        repository.getHigherStream(),
    ) { last, first, lower, higher ->
        Progress(last, first, lower, higher)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Progress(),
    )

    val goalState = goalRepository.getLastStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null,
    )
}
