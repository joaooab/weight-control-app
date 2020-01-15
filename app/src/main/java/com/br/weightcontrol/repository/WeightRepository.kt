package com.br.weightcontrol.repository

import com.br.weightcontrol.dao.WeightDao
import com.br.weightcontrol.data.Weight

interface WeightRepository : WeightDao {

}

class WeightRepositoryImpl(private val dao: WeightDao) : WeightRepository {

    override suspend fun getUsers(): List<Weight> = dao.getUsers()

    override suspend fun insertUser(weight: Weight) = dao.insertUser(weight)

    override suspend fun updateUser(weight: Weight) = dao.updateUser(weight)

    override suspend fun deleteUser(weight: Weight) = dao.deleteUser(weight)

}