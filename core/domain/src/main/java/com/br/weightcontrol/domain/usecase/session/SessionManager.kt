package com.br.weightcontrol.domain.usecase.session

import kotlinx.coroutines.flow.StateFlow


interface SessionManager {

    val state: StateFlow<SessionState>
}