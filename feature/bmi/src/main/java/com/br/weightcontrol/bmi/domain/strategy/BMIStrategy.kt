package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.domain.BMI

interface BMIStrategy {

    fun shouldExecute(value: Double): Boolean

    fun execute(value: Double): BMI
}