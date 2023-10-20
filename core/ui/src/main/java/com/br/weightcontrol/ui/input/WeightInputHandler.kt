package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.util.getValidatedDecimalNumber

object WeightInputHandler : InputHandler {

    override fun getError(input: String): Int? {
        val inputFormatted = getValidatedDecimalNumber(input).toDoubleOrNull()
        return when {
            input.isBlank() -> R.string.field_required_error
            inputFormatted == null -> R.string.field_only_numbers_error
            inputFormatted !in 10.0..400.0 -> R.string.field_weight_range_error
            else -> null
        }
    }
}

fun InputWrapper.isValidWeight() = WeightInputHandler.isValid(this)