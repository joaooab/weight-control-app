package com.br.weightcontrol.bmi.domain

import com.br.weightcontrol.bmi.domain.strategy.*
import com.br.weightcontrol.model.User
import java.math.BigDecimal
import java.math.RoundingMode

fun calculateBMI(weight: Double, user: User): BMI {
    val height = user.height.toDouble().div(100)

    return calculateBMI(weight, height)
}

internal fun calculateBMI(weight: Double, height: Double): BMI {
    val bmi = BigDecimal(weight / (height * height))
        .setScale(1, RoundingMode.HALF_UP)
        .toDouble()

    return findBMIStrategy(bmi).execute(bmi)
}

internal fun findBMIStrategy(bmi: Double): BMIStrategy {
    for (strategy in bmiStrategyList) {
        if (strategy.shouldExecute(bmi)) {
            return strategy
        }
    }

    return UnknownStrategy()
}

private val bmiStrategyList = listOf(
    ThinnessSevereStrategy(),
    ThinnessModerateStrategy(),
    ThinnessMildStrategy(),
    NormalStrategy(),
    OverweightStrategy(),
    ObeseStrategy(),
    ObeseSevereStrategy(),
    ObeseExtremeStrategy()
)