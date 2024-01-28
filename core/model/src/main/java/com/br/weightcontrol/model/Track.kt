package com.br.weightcontrol.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

data class Track(
    val id: Long = 0,
    val weight: Double = 0.0,
    val createdAt: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
)

fun Track?.format() = if (this == null) "" else weight.formatWeight()

fun Double.formatWeight() = "$this Kg"

fun Float.formatWeight() = "${String.format("%.1f", this)} Kg"
