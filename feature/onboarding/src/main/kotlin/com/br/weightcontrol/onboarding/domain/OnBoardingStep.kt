package com.br.weightcontrol.onboarding.domain

import androidx.annotation.StringRes
import com.br.weightcontrol.onboarding.R

sealed class OnBoardingStep(
    val position: Int,
    @StringRes val description: Int,
    @StringRes val buttonText: Int,
) {
    fun next() = steps.getOrNull(position.plus(1))
    fun previews() = steps.getOrNull(position.minus(1))

    class FirstStep : OnBoardingStep(
        position = 0,
        description = R.string.message_first_setp,
        buttonText = R.string.button_first_setp,
    )

    class SecondStep : OnBoardingStep(
        position = 1,
        description = R.string.message_second_setp,
        buttonText = R.string.button_second_setp,
    )

    class ThirdStep : OnBoardingStep(
        position = 2,
        description = R.string.message_third_setp,
        buttonText = R.string.button_third_setp,
    )

    companion object {
        val steps = listOf(FirstStep(), SecondStep(), ThirdStep())
    }
}
