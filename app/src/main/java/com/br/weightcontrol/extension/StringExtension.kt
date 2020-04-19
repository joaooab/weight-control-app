package com.br.weightcontrol.extension

fun String?.parseDouble(): Double {
    if (this.isNullOrEmpty()) return 0.0
    return this.replace(",", "").toDouble()
}
