package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ObeseSevereStrategy : BMIStrategy {
    override fun shouldExecute(value: Double) = value in 35.0..39.99

    override fun execute(value: Double) = BMI(value, R.string.bmi_obese_severe)
}