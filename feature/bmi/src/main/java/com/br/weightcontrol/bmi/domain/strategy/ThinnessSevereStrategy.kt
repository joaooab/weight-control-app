package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ThinnessSevereStrategy : BMIStrategy {
    override fun shouldExecute(value: Double): Boolean = value < 16.0

    override fun execute(value: Double) = BMI(value, R.string.bmi_thinness_severe)
}