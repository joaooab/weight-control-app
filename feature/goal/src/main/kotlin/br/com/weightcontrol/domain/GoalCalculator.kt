package br.com.weightcontrol.domain

import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.Track

fun calculateGoalPercentage(goal: Goal, currentTrack: Track) =
    calculateGoalPercentage(goal.start, currentTrack.weight, goal.desire)
        .coerceIn(0.0, 1.0)
        .toFloat()

private fun calculateGoalPercentage(begin: Double, current: Double, end: Double): Double {
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
    val totalGoal = end - begin
    val totalCurrent = current - begin
    return totalCurrent / totalGoal
}

private fun calculateDescPercentage(
    begin: Double,
    current: Double,
    end: Double
): Double {
    val totalGoal = begin - end
    val totalCurrent = begin - current
    return totalCurrent / totalGoal
}