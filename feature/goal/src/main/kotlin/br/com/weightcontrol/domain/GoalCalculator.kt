package br.com.weightcontrol.domain

import com.br.weightcontrol.model.Goal

fun calculatePercentage(goal: Goal): Float {
    return calculatePercentage(goal.start, 0.0, goal.desire).toFloat()
}

private fun calculatePercentage(begin: Double, current: Double, end: Double): Double {
    return if (begin > end) {
        calculateDescPercentage(begin, current, end)
    } else {
        calculateAscPercentage(begin, current, end)
    }
}

private fun calculateAscPercentage(
    begin: Double,
    current: Double,
    end: Double
): Double {
    return if (begin < current) {
        val totalGoal = end - begin
        val totalCurrent = current - begin
        totalCurrent / totalGoal * 100
    } else {
        0.0
    }
}

private fun calculateDescPercentage(
    begin: Double,
    current: Double,
    end: Double
): Double {
    return if (begin > current) {
        val totalGoal = begin - end
        val totalCurrent = begin - current
        totalCurrent / totalGoal * 100
    } else {
        0.0
    }
}