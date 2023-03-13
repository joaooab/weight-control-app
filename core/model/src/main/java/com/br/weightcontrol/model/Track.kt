package com.br.weightcontrol.model

data class Track(
    val id: Long = -1,
    val weight: Double = 0.0,
    val createdAt: String = "",
)

fun Track?.format() = if (this == null) "" else "$weight Kg"

fun Track?.formatBmi() = if (this == null) "" else "$weight Kg/m²"