package com.br.weightcontrol.model

data class Goal(
    val id: Long = 0,
    val start: Double,
    val desire: Double,
    val createdAt: String,
    val completedAt: String? = null,
) {
    fun remaining(weight: Double) = if (start < desire) desire - weight else weight - desire
}

