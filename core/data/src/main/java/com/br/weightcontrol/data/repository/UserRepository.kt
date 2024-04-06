package com.br.weightcontrol.data.repository

import com.br.weightcontrol.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val stream : Flow<User?>

    suspend fun insert(user: User): Boolean
}