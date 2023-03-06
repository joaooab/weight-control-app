package com.br.weightcontrol.dao

import androidx.room.*
import com.br.weightcontrol.model.GoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Query("SELECT * FROM goal")
    fun getAllStream(): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goal ORDER BY id DESC LIMIT 1;")
    fun getLastStream(): Flow<GoalEntity?>

    @Insert
    suspend fun insert(goal: GoalEntity)

    @Update
    suspend fun update(goal: GoalEntity)

    @Delete
    suspend fun delete(goal: GoalEntity)
}