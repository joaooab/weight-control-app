package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ThinnessModerateStrategy : BMIStrategy {

    override val minWeight = 16.0

    override val maxWeight = 16.99

    override fun execute(value: Double) = BMI(value, R.string.bmi_thinness_moderate)
}