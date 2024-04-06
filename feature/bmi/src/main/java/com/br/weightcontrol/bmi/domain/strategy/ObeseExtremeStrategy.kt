package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ObeseExtremeStrategy : BMIStrategy {

    override val minWeight: Double = 40.0

    override val maxWeight: Double = Double.MAX_VALUE

    override fun execute(value: Double) = BMI(value, R.string.bmi_obese_extremely)
}
