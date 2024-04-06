package com.br.weightcontrol.track

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.domain.usecase.AddTrackUseCase
import com.br.weightcontrol.model.ActionState
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.ui.input.DateInputHandler
import com.br.weightcontrol.ui.input.InputWrapper
import com.br.weightcontrol.ui.input.WeightInputHandler
import com.br.weightcontrol.ui.input.isValidDate
import com.br.weightcontrol.ui.input.isValidWeight
import com.br.weightcontrol.util.toLocalDate
import com.br.weightcontrol.util.todayAsString
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class TrackViewModel(
    private val handle: SavedStateHandle,
    private val addTrackUseCase: AddTrackUseCase
) : ViewModel() {

    val saveActionState = mutableStateOf<ActionState>(ActionState.Start)
    val weight = handle.getStateFlow(WEIGHT, InputWrapper())
    val date = handle.getStateFlow(DATE, InputWrapper(todayAsString()))
    val areInputsValid = combine(weight, date) { weight, date ->
        weight.isValidWeight() and date.isValidDate()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    fun onWeightEntered(input: String) {
        handle[WEIGHT] = WeightInputHandler.onInputEntered(input)
    }

    fun onDateEntered(input: LocalDate) {
        handle[DATE] = DateInputHandler.onInputEntered(input.toString())
    }

    fun save() {
        viewModelScope.launch {
            createTrack()
                .onSuccess {
                    addTrackUseCase(it)
                    saveActionState.value = ActionState.Success
                }.onFailure {
                    saveActionState.value = ActionState.Failure
                }
        }
    }


    private fun createTrack() = runCatching {
        Track(
            weight = weight.value.input.toDouble(),
            createdAt = date.value.input.toLocalDate(),
        )
    }

    fun clearSaveAction() {
        saveActionState.value = ActionState.Start
    }

    companion object {
        private const val WEIGHT = "weight"
        private const val DATE = "date"
    }
}
