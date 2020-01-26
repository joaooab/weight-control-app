package com.br.weightcontrol

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.br.weightcontrol.data.goal.Goal
import com.br.weightcontrol.data.goal.GoalDao
import com.br.weightcontrol.data.weight.WeightDao
import com.br.weightcontrol.data.weight.Weight

@Database(entities = [Weight::class, Goal::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weightDao(): WeightDao
    abstract fun goalDao(): GoalDao

    companion object {
        private const val DATABASE_NAME = "weightcontroldb"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }

}
