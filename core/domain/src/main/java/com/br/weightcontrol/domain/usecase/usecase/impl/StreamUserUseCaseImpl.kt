package com.br.weightcontrol.domain.usecase.usecase.impl

import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.domain.usecase.usecase.StreamUserUseCase
import com.br.weightcontrol.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class StreamUserUseCaseImpl(private val repository: UserRepository) : StreamUserUseCase {

    override fun invoke(): Flow<User?> = repository.userData.map { user ->
        if (user.name.isBlank()) null else user
    }
}