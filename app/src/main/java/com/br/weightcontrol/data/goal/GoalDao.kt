package com.br.weightcontrol.data.goal

import androidx.room.*

@Dao
interface GoalDao {

    @Query("SELECT * FROM goal")
    suspend fun get(): List<Goal>

    @Query("SELECT * FROM goal ORDER BY id DESC LIMIT 1;")
    suspend fun getLast(): Goal?

    @Query("SELECT count(*) FROM goal WHERE finished = 1")
    suspend fun getCountFinished(): Int

    @Insert
    suspend fun insert(goal: Goal)

    @Update
    suspend fun update(goal: Goal)

    @Delete
    suspend fun delete(goal: Goal)
}