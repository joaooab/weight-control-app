package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ObeseStrategy : BMIStrategy {

    override val minWeight: Double = 30.0

    override val maxWeight: Double = 34.99

    override fun execute(value: Double) = BMI(value, R.string.bmi_obese)
}
