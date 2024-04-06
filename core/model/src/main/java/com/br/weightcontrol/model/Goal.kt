package com.br.weightcontrol.model

data class Goal(
    val id: Long = 0,
    val start: Double,
    val desire: Double,
    val createdAt: String,
    val completedAt: String? = null,
) {
    fun remaining(weight: Double) = if (start < desire) desire - weight else weight - desire
    fun isCompleted() = completedAt != null

    fun shouldComplete(track: Track) = if (isGoalIncreaseWeight()) {
        track.weight >= desire
    } else {
        track.weight <= desire
    }

    private fun isGoalIncreaseWeight() = start < desire
}

