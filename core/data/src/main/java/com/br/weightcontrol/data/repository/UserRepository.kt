package com.br.weightcontrol.data.repository

import com.br.weightcontrol.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val userData : Flow<User>

    suspend fun insert(user: User): Boolean
}