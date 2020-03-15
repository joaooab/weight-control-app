package com.br.weightcontrol.extension

fun Double.percentFormat() : String {
    if(this.isNaN()) return "0,0%"
    return String.format("%.1f", this)
}