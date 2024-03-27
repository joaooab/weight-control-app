package com.br.weightcontrol.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.onboarding.domain.OnBoardingStep
import com.br.weightcontrol.onboarding.domain.OnBoardingUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class OnBoardingViewModel(userRepository: UserRepository) : ViewModel() {

    private val step = MutableStateFlow<OnBoardingStep>(OnBoardingStep.FirstStep())
    private val createProfile = MutableStateFlow(false)

    val uiState = combine(
        step,
        userRepository.stream,
        createProfile
    ) { step, user, onCreateProfile ->
        OnBoardingUIState(
            step = step,
            onCreateProfile = onCreateProfile,
            onFinish = user != null
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = OnBoardingUIState()
    )

    fun onNext(step: OnBoardingStep) {
        if (step is OnBoardingStep.ThirdStep) createProfile.value = true
        step.next()?.let {
            this.step.value = it
        }
    }

    fun onPreviews(step: OnBoardingStep) {
        step.previews()?.let {
            this.step.value = it
        }
    }

    fun onCreateProfile() {
        createProfile.value = false
    }
}