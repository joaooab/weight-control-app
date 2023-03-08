package com.br.weightcontrol.data.repository.impl

import com.br.weightcontrol.dao.GoalDao
import com.br.weightcontrol.data.mapper.toEntity
import com.br.weightcontrol.data.mapper.toModel
import com.br.weightcontrol.data.repository.GoalRepository
import com.br.weightcontrol.model.Goal
import kotlinx.coroutines.flow.map

class GoalRepositoryImpl(private val dao: GoalDao) : GoalRepository {

    override fun getAllStream() = dao.getAllStream().map { it.toModel() }

    override fun getLastStream() = dao.getLastStream().map { it?.toModel() }

    override suspend fun insert(goal: Goal) {
        dao.insert(goal.toEntity())
    }

    override suspend fun update(goal: Goal) {
        dao.update(goal.toEntity())
    }

    override suspend fun delete(goal: Goal) {
        dao.delete(goal.toEntity())
    }
}
