package com.br.weightcontrol.model

import kotlinx.datetime.LocalDate

data class Profile(
    val name: String,
    val height: Int,
    val birthday: LocalDate,
    val gender: Gender
)