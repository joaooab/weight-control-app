package com.br.weightcontrol.domain.usecase.session.impl

import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.domain.usecase.session.SessionManager
import com.br.weightcontrol.domain.usecase.session.SessionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class SessionManagerImpl(
    userRepository: UserRepository,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
) : SessionManager {

    override val state: StateFlow<SessionState> = userRepository.stream.map { user ->
        when (user) {
            null -> SessionState.OnBoarding
            else -> SessionState.Logged(user)
        }
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SessionState.Loading,
    )
}
