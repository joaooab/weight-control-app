package com.br.weightcontrol.domain.usecase.usecase.impl

import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.domain.usecase.session.Session
import com.br.weightcontrol.domain.usecase.session.SessionState
import com.br.weightcontrol.domain.usecase.usecase.InitSessionUseCase
import kotlinx.coroutines.flow.firstOrNull

internal class InitSessionUseCaseImpl(
    private val userRepository: UserRepository
) : InitSessionUseCase {

    override suspend fun invoke(): SessionState {
        val user = userRepository.userData.firstOrNull()
        return if (user == null || user.name.isBlank()) SessionState.OnBoarding
        else SessionState.Logged(Session(user))
    }
}