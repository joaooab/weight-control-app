package com.br.weightcontrol.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Track")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val weight: Double,
    val createdAt: String,
)