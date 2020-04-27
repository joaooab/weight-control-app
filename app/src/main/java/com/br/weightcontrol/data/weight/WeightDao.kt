package com.br.weightcontrol.data.weight

import androidx.room.*

@Dao
interface WeightDao {

    @Query("SELECT * FROM weight")
    suspend fun get(): List<Weight>

    @Query("SELECT * FROM weight WHERE date = :date")
    suspend fun getByDate(date: String): Weight?

    @Query("SELECT * FROM weight ORDER BY id DESC LIMIT 1;")
    suspend fun getLast(): Weight?

    @Query("SELECT * FROM weight ORDER BY id ASC LIMIT 1;")
    suspend fun getFirst(): Weight?

    @Query("SELECT * FROM weight ORDER BY weight DESC LIMIT 1;")
    suspend fun getHigher(): Weight?

    @Query("SELECT * FROM weight ORDER BY weight ASC LIMIT 1;")
    suspend fun getLower(): Weight?

    @Insert
    suspend fun insert(weight: Weight)

    @Update
    suspend fun update(weight: Weight)

    @Delete
    suspend fun delete(weight: Weight)
}