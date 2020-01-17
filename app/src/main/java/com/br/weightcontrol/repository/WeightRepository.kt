package com.br.weightcontrol.repository

import com.br.weightcontrol.dao.WeightDao
import com.br.weightcontrol.data.Weight

interface WeightRepository : WeightDao {

}

class WeightRepositoryImpl(private val dao: WeightDao) : WeightRepository {

    override suspend fun get(): List<Weight> = dao.get()

    override suspend fun getByDate(date: String): Weight = dao.getByDate(date)

    override suspend fun getLast(): Weight = dao.getLast()

    override suspend fun insert(weight: Weight) = dao.insert(weight)

    override suspend fun update(weight: Weight) = dao.update(weight)

    override suspend fun delete(weight: Weight) = dao.delete(weight)

}