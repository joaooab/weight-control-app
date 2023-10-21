package com.br.weightcontrol.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

private const val DATE_PATTERN = "yyyy-MM-dd"
private const val DATE_PATTERN_BR = "dd/MM/yyyy"

fun today() = Clock.System.todayIn(TimeZone.UTC)
fun todayAsString() = today().toString()
fun defaultBirthday() = LocalDate(2000, 1, 1)
fun String.toLocalDate() = LocalDate.parse(this)
fun String.toLocalDateOrNull() = runCatching { LocalDate.parse(this) }.getOrNull()
fun Long.toLocalDate() = Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC).date
fun LocalDate.toLong() = this.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()

fun calculateAge(birthdate: LocalDate): Int {
    val currentDate = today()
    val years = currentDate.year - birthdate.year
    val currentMonth = currentDate.monthNumber
    val birthMonth = birthdate.monthNumber
    val currentDay = currentDate.dayOfMonth
    val birthDay = birthdate.dayOfMonth

    if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
        return years - 1
    }

    return years
}