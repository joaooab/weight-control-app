package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeightInputHandler(override val input: String = "") : InputHandler {

    override fun validate() {
        check(input.toIntOrNull() in 100..300)
    }

    override fun error() = runCatching { validate() }
        .mapCatching { R.string.field_height_error }
        .getOrNull()
}