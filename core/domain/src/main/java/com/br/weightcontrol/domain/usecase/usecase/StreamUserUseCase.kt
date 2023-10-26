package com.br.weightcontrol.domain.usecase.usecase

import com.br.weightcontrol.model.User
import kotlinx.coroutines.flow.Flow

interface StreamUserUseCase {

    operator fun invoke(): Flow<User?>
}