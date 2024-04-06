package com.br.weightcontrol.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

data class User(
    val name: String = "",
    val height: Int = 0,
    val birthday: LocalDate = Clock.System.todayIn(TimeZone.UTC),
    val gender: Gender = Gender.MALE
)