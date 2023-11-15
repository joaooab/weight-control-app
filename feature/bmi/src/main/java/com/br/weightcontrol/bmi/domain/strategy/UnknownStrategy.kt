package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class UnknownStrategy : BMIStrategy {

    override val minWeight = Double.MIN_VALUE

    override val maxWeight = Double.MAX_VALUE

    override fun shouldExecute(value: Double) = false

    override fun execute(value: Double) = BMI(value, R.string.bmi_unknown)
}
