package com.br.weightcontrol.model

data class Goal(
    val id: Long,
    val start: Double,
    val desire: Double,
    val current: Double,
    val createdAt: String,
    val completedAt: String? = null,
)

