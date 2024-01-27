package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.util.onChangeWithLimit

object HeightInputHandler : InputHandler {

    override fun getError(input: String): Int? {
        val inputFormatted = onChangeWithLimit(input, 3).toIntOrNull()
        return when {
            input.isBlank() -> R.string.field_required_error
            inputFormatted == null -> R.string.field_only_numbers_error
            inputFormatted < 50.0 -> R.string.field_height_min_error
            inputFormatted > 300.0 -> R.string.field_height_max_error
            else -> null
        }
    }
}

fun InputWrapper.isValidHeight() = HeightInputHandler.isValid(this)