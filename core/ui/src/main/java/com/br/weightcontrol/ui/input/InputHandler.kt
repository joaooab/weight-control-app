package com.br.weightcontrol.ui.input

interface InputHandler {

    fun onInputEntered(input: String) = InputWrapper(input, getError(input))

    fun isValid(input: InputWrapper) = getError(input.input) == null

    fun getError(input: String): Int?
}