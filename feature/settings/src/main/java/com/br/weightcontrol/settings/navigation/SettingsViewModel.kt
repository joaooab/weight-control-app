package com.br.weightcontrol.settings.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.GoalRepository
import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.User
import com.br.weightcontrol.ui.domain.DeleteDialogState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    userRepository: UserRepository,
    private val goalRepository: GoalRepository
) : ViewModel() {

    val user = userRepository.stream.filterNotNull().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = User(),
    )

    val goal = goalRepository.stream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null,
    )


    private val _showDeleteDialog = MutableStateFlow<DeleteDialogState<Goal>>(DeleteDialogState.Hide())
    val showDeleteDialog: StateFlow<DeleteDialogState<Goal>> = _showDeleteDialog.asStateFlow()

    fun onDelete(goal: Goal) {
        _showDeleteDialog.value = DeleteDialogState.Open(goal)
    }

    fun onDeleteConfirm(goal: Goal) {
        viewModelScope.launch {
            goalRepository.delete(goal)
            onDeleteDismiss()
        }
    }

    fun onDeleteDismiss() {
        _showDeleteDialog.value = DeleteDialogState.Hide()
    }
}