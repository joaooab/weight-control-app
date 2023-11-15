package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.domain.BMI

interface BMIStrategy {

    val minWeight: Double

    val maxWeight: Double

    fun shouldExecute(value: Double) = value in minWeight..maxWeight

    fun execute(value: Double): BMI
}