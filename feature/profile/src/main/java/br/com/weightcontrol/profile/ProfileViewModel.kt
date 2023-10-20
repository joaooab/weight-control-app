package br.com.weightcontrol.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.model.ActionState
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.model.User
import com.br.weightcontrol.ui.input.DateInputHandler
import com.br.weightcontrol.ui.input.HeightInputHandler
import com.br.weightcontrol.ui.input.InputWrapper
import com.br.weightcontrol.ui.input.NameInputHandler
import com.br.weightcontrol.ui.input.isValidDate
import com.br.weightcontrol.ui.input.isValidHeight
import com.br.weightcontrol.ui.input.isValidName
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate

class ProfileViewModel(
    private val handle: SavedStateHandle,
    private val repository: UserRepository
) : ViewModel() {

    val saveActionState = mutableStateOf<ActionState>(ActionState.Start)
    val name = handle.getStateFlow(NAME, InputWrapper())
    val height = handle.getStateFlow(HEIGHT, InputWrapper())
    val birthday = handle.getStateFlow(BIRTHDAY, InputWrapper())
    val gender = handle.getStateFlow(GENDER, InputWrapper(Gender.MALE.name))
    val areInputsValid = combine(name, height, birthday) { name, height, birthday ->
        name.isValidName() and height.isValidHeight() and birthday.isValidDate()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    fun onNameEntered(input: String) {
        handle[NAME] = NameInputHandler.onInputEntered(input)
    }

    fun onHeightEntered(input: String) {
        handle[HEIGHT] = HeightInputHandler.onInputEntered(input)
    }

    fun onBirthdayEntered(input: LocalDate) {
        handle[BIRTHDAY] = DateInputHandler.onInputEntered(input.toString())
    }

    fun onGenderEntered(input: Gender) {
        handle[GENDER] = InputWrapper(input.name, null)
    }

    fun save() {
        viewModelScope.launch {
            createProfile()
                .onSuccess {
                    if (repository.insert(it)) saveActionState.value = ActionState.Success
                    else saveActionState.value = ActionState.Failure
                }.onFailure {
                    saveActionState.value = ActionState.Failure
                }
        }
    }

    fun clearSaveAction() {
        saveActionState.value = ActionState.Start
    }

    private fun createProfile() = runCatching {
        User(
            name = name.value.input,
            height = height.value.input.toInt(),
            birthday = birthday.value.input.toLocalDate(),
            gender = Gender.valueOf(gender.value.input)
        )
    }

    companion object {
        private const val NAME = "name"
        private const val HEIGHT = "height"
        private const val BIRTHDAY = "birthday"
        private const val GENDER = "gender"
    }
}