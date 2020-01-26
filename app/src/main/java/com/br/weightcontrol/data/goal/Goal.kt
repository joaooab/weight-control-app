package com.br.weightcontrol.data.goal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo val begin: Double = 0.0,
    @ColumnInfo val end: Double = 0.0,
    @ColumnInfo(name = "finished") val isFinished: Boolean = false
)