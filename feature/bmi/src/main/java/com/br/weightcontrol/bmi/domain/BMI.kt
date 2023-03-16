package com.br.weightcontrol.bmi.domain

import androidx.annotation.StringRes

data class BMI(
    val value: Double,
    @StringRes val message: Int,
)