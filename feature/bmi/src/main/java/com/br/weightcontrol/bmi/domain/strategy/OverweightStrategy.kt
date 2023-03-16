package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class OverweightStrategy : BMIStrategy {
    override fun shouldExecute(value: Double) = value in 25.0..29.99

    override fun execute(value: Double) = BMI(value, R.string.bmi_overweight)
}
