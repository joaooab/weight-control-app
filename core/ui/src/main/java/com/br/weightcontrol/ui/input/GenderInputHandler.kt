package com.br.weightcontrol.ui.input

import com.br.weightcontrol.model.Gender
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenderInputHandler(override val input: String = Gender.MALE.name) : InputHandler {

    override fun validate() {}

    override fun error() = -1
}