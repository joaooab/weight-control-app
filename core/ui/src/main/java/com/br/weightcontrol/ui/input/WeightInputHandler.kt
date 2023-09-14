package com.br.weightcontrol.ui.input

import com.br.weightcontrol.core.ui.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeightInputHandler(override val input: String = "") : InputHandler {

    override fun validate() {
        check(input.toDouble() in 10.0..400.0)
    }

    override fun error() = runCatching { validate() }
        .mapCatching { R.string.field_weight_error }
        .getOrNull()
}