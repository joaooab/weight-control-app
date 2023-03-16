package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ObeseStrategy : BMIStrategy {
    override fun shouldExecute(value: Double) = value in 30.0..34.99

    override fun execute(value: Double) = BMI(value, R.string.bmi_obese)
}
