package com.br.weightcontrol.bmi.domain.strategy

import com.br.weightcontrol.bmi.R
import com.br.weightcontrol.bmi.domain.BMI

class UnknownStrategy : BMIStrategy {

    override fun shouldExecute(value: Double) = false

    override fun execute(value: Double) = BMI(value, R.string.bmi_unknown)
}
