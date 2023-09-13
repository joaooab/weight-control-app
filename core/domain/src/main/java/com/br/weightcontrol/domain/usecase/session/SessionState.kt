package com.br.weightcontrol.domain.usecase.session

interface SessionState {
    object Loading: SessionState
    object OnBoarding: SessionState
    class Logged(val session: Session): SessionState
}