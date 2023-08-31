package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.util.toLocalDate
import kotlinx.parcelize.Parcelize

@Parcelize
data class BirthDayInputHandler(override val input: String = "") : InputHandler {

    override fun validate() {
        input.toLocalDate()
    }

    override fun error() = runCatching { validate() }
        .mapCatching { R.string.field_birthday_error }
        .getOrNull()
}