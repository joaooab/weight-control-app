package com.br.weightcontrol.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Goal")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val start: Double,
    val desire: Double,
    val current: Double,
    val createdAt: String,
    val completedAt: String?,
)
