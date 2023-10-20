package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R

object NameInputHandler : InputHandler {

    override fun getError(input: String) = when {
        input.isBlank() -> R.string.field_required_error
        else -> null
    }
}

fun InputWrapper.isValidName() = NameInputHandler.isValid(this)