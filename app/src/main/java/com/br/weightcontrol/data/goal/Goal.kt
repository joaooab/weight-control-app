package com.br.weightcontrol.data.goal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo val begin: Double = 0.0,
    @ColumnInfo val end: Double = 0.0,
    @ColumnInfo var current: Double = 0.0,
    @ColumnInfo(name = "date_start") val dateStart: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "date_finish") var dateFinish: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "finished") var isFinished: Boolean = false
)