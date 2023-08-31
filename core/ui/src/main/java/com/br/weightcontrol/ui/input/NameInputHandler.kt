package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class NameInputHandler(override val input: String = "") : InputHandler {

    override fun validate() {
        check(input.isNotBlank())
    }

    override fun error() = runCatching { validate() }
        .mapCatching { R.string.field_name_error }
        .getOrNull()
}