package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI


class NormalStrategy : BMIStrategy {
    override val minWeight: Double = 18.5

    override val maxWeight: Double = 24.99

    override fun execute(value: Double) = BMI(value, R.string.bmi_normal)
}
