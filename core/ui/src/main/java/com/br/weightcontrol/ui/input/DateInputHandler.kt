package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.util.toLocalDateOrNull

object DateInputHandler : InputHandler {

    override fun getError(input: String) = when (input.toLocalDateOrNull()) {
        null -> R.string.field_date_error
        else -> null
    }
}

fun InputWrapper.isValidDate() = DateInputHandler.isValid(this)