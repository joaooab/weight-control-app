package com.br.weightcontrol.data.repository

import com.br.weightcontrol.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {

    fun stream(): Flow<Goal?>

    suspend fun get(): Goal?

    suspend fun insert(goal: Goal)

    suspend fun update(goal: Goal)

    suspend fun delete(goal: Goal)
}