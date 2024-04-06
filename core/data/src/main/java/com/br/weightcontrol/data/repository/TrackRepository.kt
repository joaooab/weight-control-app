package com.br.weightcontrol.data.repository

import com.br.weightcontrol.model.Track
import kotlinx.coroutines.flow.Flow

interface TrackRepository {

    fun getAllStreamOrderedByCreatedAtDesc(): Flow<List<Track>>

    fun getAllStreamOrderedByCreatedAtAsc(): Flow<List<Track>>

    fun getFirstStream(): Flow<Track?>

    fun getLastStream(): Flow<Track?>

    fun getPreviewsStream(): Flow<Track?>

    fun getHigherStream(): Flow<Track?>

    fun getLowerStream(): Flow<Track?>

    fun getByCreatedAt(createdAt: String): Flow<Track?>

    suspend fun insert(track: Track)

    suspend fun update(track: Track)

    suspend fun delete(track: Track)
}