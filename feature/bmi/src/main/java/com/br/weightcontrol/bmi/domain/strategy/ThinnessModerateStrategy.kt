package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ThinnessModerateStrategy : BMIStrategy {
    override fun shouldExecute(value: Double): Boolean {
        return value in 16.0..16.99
    }

    override fun execute(value: Double) = BMI(value, R.string.bmi_thinness_moderate)
}