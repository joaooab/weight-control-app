package com.br.weightcontrol.bmi.domain

import com.br.weightcontrol.bmi.domain.strategy.BMIStrategy
import com.br.weightcontrol.bmi.domain.strategy.NormalStrategy
import com.br.weightcontrol.bmi.domain.strategy.ObeseExtremeStrategy
import com.br.weightcontrol.bmi.domain.strategy.ObeseSevereStrategy
import com.br.weightcontrol.bmi.domain.strategy.ObeseStrategy
import com.br.weightcontrol.bmi.domain.strategy.OverweightStrategy
import com.br.weightcontrol.bmi.domain.strategy.ThinnessMildStrategy
import com.br.weightcontrol.bmi.domain.strategy.ThinnessModerateStrategy
import com.br.weightcontrol.bmi.domain.strategy.ThinnessSevereStrategy
import com.br.weightcontrol.bmi.domain.strategy.UnknownStrategy
import com.br.weightcontrol.model.User
import com.br.weightcontrol.util.calculateWithScale

fun calculateBMI(weight: Double, user: User): BMI {
    val height = user.height.toDouble().div(100)

    return calculateBMI(weight, height)
}

fun calculateRecommendation(user: User): WeightRecommendation {
    val height = user.height.toDouble().div(100)
    val strategy = NormalStrategy()

    val minWeight = calculateWithScale { strategy.minWeight * (height * height) }
    val maxWeight = calculateWithScale { strategy.maxWeight * (height * height) }

    return WeightRecommendation(minWeight, maxWeight)
}

internal fun calculateBMI(weight: Double, height: Double): BMI {
    val bmi = calculateWithScale { weight / (height * height) }

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