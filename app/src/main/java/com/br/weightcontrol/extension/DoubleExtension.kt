package com.br.weightcontrol.extension

import java.text.DecimalFormat

fun Double.percentFormat(): String {
    if (this.isNaN()) return "0,0%"
    return String.format("%.1f", this)
}

fun Double?.decimalFormat(): String = DecimalFormat().format(this) ?: "0.0"
