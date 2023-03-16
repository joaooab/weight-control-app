package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ObeseExtremeStrategy : BMIStrategy {

    override fun shouldExecute(value: Double) = value >= 40.0

    override fun execute(value: Double) = BMI(value, R.string.bmi_obese_extremely)
}
