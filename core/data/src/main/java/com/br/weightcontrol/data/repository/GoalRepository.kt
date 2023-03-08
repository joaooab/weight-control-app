package com.br.weightcontrol.data.repository

import com.br.weightcontrol.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {

    fun getAllStream(): Flow<List<Goal>>

    fun getLastStream(): Flow<Goal?>

    suspend fun insert(goal: Goal)

    suspend fun update(goal: Goal)

    suspend fun delete(goal: Goal)
}