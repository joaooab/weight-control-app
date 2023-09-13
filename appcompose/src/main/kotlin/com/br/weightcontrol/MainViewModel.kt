package com.br.weightcontrol

import androidx.lifecycle.ViewModel
import com.br.weightcontrol.domain.usecase.session.SessionManager
import com.br.weightcontrol.domain.usecase.session.SessionState
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(sessionManager: SessionManager) : ViewModel() {

    val uiState: StateFlow<SessionState> = sessionManager.state
}
