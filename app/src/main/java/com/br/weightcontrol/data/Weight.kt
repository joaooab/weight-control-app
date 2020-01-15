package com.br.weightcontrol.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weight(
    @PrimaryKey val id: Long,
    @ColumnInfo val weight: Double = 0.0
)