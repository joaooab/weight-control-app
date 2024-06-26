package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.util.getValidatedDecimalNumber

object WeightInputHandler : InputHandler {

    override fun onInputEntered(input: String) =
        InputWrapper(getValidatedDecimalNumber(input), getError(input))

    override fun getError(input: String): Int? {
        val inputFormatted = input.toDoubleOrNull()
        return when {
            input.isBlank() -> R.string.field_required_error
            inputFormatted == null -> R.string.field_only_numbers_error
            inputFormatted < 10.0 -> R.string.field_weight_min_error
            inputFormatted > 1000.0 -> R.string.field_weight_max_error
            else -> null
        }
    }
}

fun InputWrapper.isValidWeight() = WeightInputHandler.isValid(this)