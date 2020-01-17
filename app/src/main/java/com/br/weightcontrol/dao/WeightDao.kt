package com.br.weightcontrol.dao

import androidx.room.*
import com.br.weightcontrol.data.Weight

@Dao
interface WeightDao {

    @Query("SELECT * FROM weight")
    suspend fun get(): List<Weight>

    @Query("SELECT * FROM weight WHERE date = :date")
    suspend fun getByDate(date: String): Weight

    @Query("SELECT * FROM weight ORDER BY id DESC LIMIT 1;")
    suspend fun getLast(): Weight

    @Insert
    suspend fun insert(weight: Weight)

    @Update
    suspend fun update(weight: Weight)

    @Delete
    suspend fun delete(weight: Weight)
}