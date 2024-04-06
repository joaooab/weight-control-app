package com.br.weightcontrol.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.TrackRepository
import com.br.weightcontrol.ui.domain.DeleteDialogState
import com.br.weightcontrol.model.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: TrackRepository) : ViewModel() {

    val tracks: Flow<List<Track>> = repository.getAllStreamOrderedByCreatedAtDesc()

    private val _showDeleteDialog = MutableStateFlow<DeleteDialogState<Track>>(DeleteDialogState.Hide())
    val showDeleteDialog: StateFlow<DeleteDialogState<Track>> = _showDeleteDialog.asStateFlow()

    fun onDelete(track: Track) {
        _showDeleteDialog.value = DeleteDialogState.Open(track)
    }

    fun onDeleteConfirm(track: Track) {
        viewModelScope.launch {
            repository.delete(track)
            onDeleteDismiss()
        }
    }

    fun onDeleteDismiss() {
        _showDeleteDialog.value = DeleteDialogState.Hide()
    }
}
