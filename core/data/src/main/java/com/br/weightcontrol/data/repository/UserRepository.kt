package com.br.weightcontrol.data.repository

import com.br.weightcontrol.model.Profile
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val userData : Flow<Profile>

    suspend fun insert(profile: Profile): Boolean
}