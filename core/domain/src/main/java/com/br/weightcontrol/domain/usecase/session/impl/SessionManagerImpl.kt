package com.br.weightcontrol.domain.usecase.session.impl

import com.br.weightcontrol.domain.usecase.session.Session
import com.br.weightcontrol.domain.usecase.session.SessionManager
import com.br.weightcontrol.domain.usecase.session.SessionState
import com.br.weightcontrol.domain.usecase.usecase.InitSessionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class SessionManagerImpl(
    private val initSessionUseCase: InitSessionUseCase
) : SessionManager {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        scope.launch {
            val state = initSessionUseCase()
            if (state is SessionState.Logged) session = state.session
            _state.emit(state)
        }
    }

    private val _state = MutableStateFlow<SessionState>(SessionState.Loading)
    override val state: StateFlow<SessionState> = _state.asStateFlow()
    override var session: Session? = null
        private set
}
