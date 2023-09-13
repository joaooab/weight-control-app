package com.br.weightcontrol.domain.usecase.usecase

import com.br.weightcontrol.domain.usecase.session.SessionState

interface InitSessionUseCase {

    suspend operator fun invoke(): SessionState
}