package com.br.weightcontrol.dao

import androidx.room.*
import com.br.weightcontrol.data.Weight

@Dao
interface WeightDao {

    @Query("SELECT * FROM weight")
    suspend fun getUsers(): List<Weight>

    @Insert
    suspend fun insertUser(weight: Weight)

    @Update
    suspend fun updateUser(weight: Weight)

    @Delete
    suspend fun deleteUser(weight: Weight)
}