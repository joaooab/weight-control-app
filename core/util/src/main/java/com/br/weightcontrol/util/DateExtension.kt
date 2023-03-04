package com.br.weightcontrol.util

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_PATTERN = "yyyy-MM-dd"
private const val DATE_PATTERN_BR = "dd/MM/yyyy"

fun today() = Clock.System.todayIn(TimeZone.currentSystemDefault())

fun parseOldDateToNewDate(date: String): String {
    val oldDate = SimpleDateFormat(
        DATE_PATTERN_BR,
        Locale.getDefault()
    ).parse(date) ?: return ""

    return SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(oldDate)
}
