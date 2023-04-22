package com.br.weightcontrol.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.TrackRepository
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.util.toLocalDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrackViewModel(private val trackRepository: TrackRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Holding)
    val uiState = _uiState.asStateFlow()

    fun save(date: String, weight: Double) {
        viewModelScope.launch {
            val track = Track(weight = weight, createdAt = date.toLocalDate())
            trackRepository.insert(track)
            _uiState.emit(UiState.Added)
        }
    }

    sealed class UiState {
        object Holding : UiState()
        object Added : UiState()
    }
}
