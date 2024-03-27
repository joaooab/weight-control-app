package com.br.weightcontrol.onboarding.domain

data class OnBoardingUIState(
    val step: OnBoardingStep = OnBoardingStep.FirstStep(),
    val onCreateProfile: Boolean = false,
    val onFinish: Boolean = false
)
