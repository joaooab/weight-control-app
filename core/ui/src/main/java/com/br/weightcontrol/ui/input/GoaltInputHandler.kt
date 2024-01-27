package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.util.getValidatedDecimalNumber

object GoalInputHandler : InputHandler {

    fun onInputEntered(input: String, currentTrack: Double) = InputWrapper(
        input = input,
        errorId = getError(input, currentTrack)
    )

    fun isValid(input: InputWrapper, currentTrack: Double) =
        getError(input.input, currentTrack) == null

    private fun getError(input: String, currentTrack: Double): Int? {
        val error = getError(input)
        val inputFormatted = getValidatedDecimalNumber(input).toDoubleOrNull()
        return when {
            error != null -> error
            inputFormatted == currentTrack -> R.string.field_goal_equal_current_error
            else -> null
        }
    }

    override fun getError(input: String): Int? {
        return WeightInputHandler.getError(input)
    }
}

fun InputWrapper.isValidGoal(currentTrack: Double) = GoalInputHandler.isValid(this, currentTrack)