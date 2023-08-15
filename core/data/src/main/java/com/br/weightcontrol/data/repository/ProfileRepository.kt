package com.br.weightcontrol.data.repository

import com.br.weightcontrol.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun stream(): Flow<Profile>

    suspend fun insert(profile: Profile)
}