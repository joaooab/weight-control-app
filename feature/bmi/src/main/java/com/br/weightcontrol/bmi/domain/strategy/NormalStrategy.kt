package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI


class NormalStrategy : BMIStrategy {
    override fun shouldExecute(value: Double) = value in 18.5..24.99

    override fun execute(value: Double) = BMI(value, R.string.bmi_normal)
}
