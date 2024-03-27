package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ObeseSevereStrategy : BMIStrategy {

    override val minWeight: Double = 35.0

    override val maxWeight: Double = 39.99

    override fun execute(value: Double) = BMI(value, R.string.bmi_obese_severe)
}