package com.br.weightcontrol.track

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.TrackRepository
import com.br.weightcontrol.model.ActionState
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.ui.input.DateInputHandler
import com.br.weightcontrol.ui.input.WeightInputHandler
import com.br.weightcontrol.util.getValidatedDecimalNumber
import com.br.weightcontrol.util.toLocalDate
import com.br.weightcontrol.util.today
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class TrackViewModel(
    private val handle: SavedStateHandle,
    private val repository: TrackRepository
) : ViewModel() {

    val saveActionState = mutableStateOf<ActionState>(ActionState.Start)
    val weight = handle.getStateFlow(WEIGHT, WeightInputHandler())
    val birthday = handle.getStateFlow(BIRTHDAY, DateInputHandler(today().toString()))
    val areInputsValid = combine(weight, birthday) { weight, birthday ->
        weight.isValid() and birthday.isValid()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    fun onWeightEntered(input: String) {
        val inputDecimalNumber = getValidatedDecimalNumber(input)
        handle[WEIGHT] = WeightInputHandler(inputDecimalNumber)
    }

    fun onBirthdayEntered(input: LocalDate) {
        handle[BIRTHDAY] = DateInputHandler(input.toString())
    }

    fun save() {
        viewModelScope.launch {
            createTrack()
                .onSuccess {
                    repository.insert(track = it)
                    saveActionState.value = ActionState.Success
                }.onFailure {
                    saveActionState.value = ActionState.Failure
                }
        }
    }


    private fun createTrack() = runCatching {
        Track(
            weight = weight.value.input.toDouble(),
            createdAt = birthday.value.input.toLocalDate(),
        )
    }

    fun clearSaveAction() {
        saveActionState.value = ActionState.Start
    }

    companion object {
        private const val WEIGHT = "weight"
        private const val BIRTHDAY = "birthday"
    }
}
