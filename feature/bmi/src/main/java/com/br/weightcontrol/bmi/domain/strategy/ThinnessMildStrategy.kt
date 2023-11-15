package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class ThinnessMildStrategy : BMIStrategy {

    override val minWeight: Double = 17.0

    override val maxWeight: Double = 18.49

    override fun execute(value: Double) = BMI(value, R.string.bmi_thinness_mild)
}