package br.com.weightcontrol.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.ProfileRepository
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.model.Profile
import com.br.weightcontrol.ui.input.BirthDayInputHandler
import com.br.weightcontrol.ui.input.GenderInputHandler
import com.br.weightcontrol.ui.input.HeightInputHandler
import com.br.weightcontrol.ui.input.NameInputHandler
import com.br.weightcontrol.util.onChangeWithLimit
import com.br.weightcontrol.util.toLocalDate
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class ProfileViewModel(
    private val handle: SavedStateHandle,
    private val repository: ProfileRepository
) : ViewModel() {

    val name = handle.getStateFlow(NAME, NameInputHandler())
    val height = handle.getStateFlow(HEIGHT, HeightInputHandler())
    val birthday = handle.getStateFlow(BIRTHDAY, BirthDayInputHandler())
    val gender = handle.getStateFlow(GENDER, GenderInputHandler())
    val areInputsValid = combine(name, height, birthday, gender) { name, height, birthday, gender ->
        name.isValid() and height.isValid() and birthday.isValid() and gender.isValid()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    fun onNameEntered(input: String) {
        handle[NAME] = NameInputHandler(input)
    }

    fun onHeightEntered(input: String) {
        val inputWithLimit = onChangeWithLimit(input, 3)
        handle[HEIGHT] = HeightInputHandler(inputWithLimit)
    }

    fun onBirthdayEntered(input: LocalDate) {
        handle[BIRTHDAY] = BirthDayInputHandler(input.toString())
    }

    fun onGenderEntered(input: Gender) {
        handle[GENDER] = GenderInputHandler(input.name)
    }

    fun save() {
        viewModelScope.launch {
            createProfile()
                .onSuccess { repository.insert(it) }
                .onFailure { }
        }
    }

    private fun createProfile() = runCatching {
        Profile(
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