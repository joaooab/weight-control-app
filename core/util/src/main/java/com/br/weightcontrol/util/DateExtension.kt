package com.br.weightcontrol.util

import kotlinx.datetime.*

private const val DATE_PATTERN = "yyyy-MM-dd"
private const val DATE_PATTERN_BR = "dd/MM/yyyy"

fun today() = Clock.System.todayIn(TimeZone.UTC)
fun todayAsString() = today().toString()
fun String.toLocalDate() = LocalDate.parse(this)
fun Long.toLocalDate() = Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC).date
fun LocalDate.toLong() = this.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()
