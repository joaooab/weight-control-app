package br.com.weightcontrol.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.ProfileRepository
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.model.Profile
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    fun save(
        name: String,
        height: Int,
        birthday: LocalDate,
        gender: Gender = Gender.MALE
    ) {
        viewModelScope.launch {
            repository.insert(
                Profile(
                    name = name,
                    height = height,
                    birthday = birthday,
                    gender = gender
                )
            )
        }
    }
}