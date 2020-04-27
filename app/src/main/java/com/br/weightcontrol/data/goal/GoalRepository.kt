package com.br.weightcontrol.data.goal

interface GoalRepository : GoalDao

class GoalRepositoryImpl(private val dao: GoalDao) :
    GoalRepository {

    override suspend fun get(): List<Goal> = dao.get()

    override suspend fun getLast(): Goal? = dao.getLast()

    override suspend fun getCountFinished(): Int = dao.getCountFinished()

    override suspend fun insert(goal: Goal) = dao.insert(goal)

    override suspend fun update(goal: Goal) = dao.update(goal)

    override suspend fun delete(goal: Goal) = dao.delete(goal)

}