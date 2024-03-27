package com.br.weightcontrol.dao

import androidx.room.*
import com.br.weightcontrol.model.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Query("SELECT * FROM track ORDER BY createdAt DESC")
    fun getAllStreamOrderedByCreatedAtDesc(): Flow<List<TrackEntity>>

    @Query("SELECT * FROM track ORDER BY createdAt ASC")
    fun getAllStreamOrderedByCreatedAtAsc(): Flow<List<TrackEntity>>

    @Query("SELECT * FROM track WHERE createdAt = :createdAt")
    fun getByCreatedAt(createdAt: String): Flow<TrackEntity?>

    @Query("SELECT * FROM track ORDER BY createdAt ASC LIMIT 1;")
    fun getFirstStream(): Flow<TrackEntity?>

    @Query("SELECT * FROM track ORDER BY createdAt DESC LIMIT 1;")
    fun getLastStream(): Flow<TrackEntity?>

    @Query("SELECT * FROM track ORDER BY createdAt DESC LIMIT 1,1;")
    fun getPreviewsStream(): Flow<TrackEntity?>

    @Query("SELECT * FROM track ORDER BY weight DESC LIMIT 1;")
    fun getHigherStream(): Flow<TrackEntity?>

    @Query("SELECT * FROM track ORDER BY weight ASC LIMIT 1;")
    fun getLowerStream(): Flow<TrackEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(track: TrackEntity)

    @Update
    suspend fun update(track: TrackEntity)

    @Delete
    suspend fun delete(track: TrackEntity)
}
