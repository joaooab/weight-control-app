package com.br.weightcontrol.domain.usecase.session

import com.br.weightcontrol.model.User

interface SessionState {
    object Loading : SessionState
    object OnBoarding : SessionState
    class Logged(val user: User) : SessionState
}