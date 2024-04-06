package com.br.weightcontrol.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.br.weightcontrol.dao.GoalDao
import com.br.weightcontrol.dao.TrackDao
import com.br.weightcontrol.model.GoalEntity
import com.br.weightcontrol.model.TrackEntity

@Database(
    entities = [
        TrackEntity::class,
        GoalEntity::class
    ],
    exportSchema = true,
    version = 2,
)
abstract class WeiDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
    abstract fun goalDao(): GoalDao
}
