package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ThinnessMildStrategy : BMIStrategy {
    override fun shouldExecute(value: Double): Boolean = value in 17.0..18.49

    override fun execute(value: Double) = BMI(value, R.string.bmi_thinness_mild)
}