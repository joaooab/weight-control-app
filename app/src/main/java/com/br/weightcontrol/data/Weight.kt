package com.br.weightcontrol.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Weight(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo val weight: Double = 0.0,
    @ColumnInfo val date: Calendar = Calendar.getInstance()
)