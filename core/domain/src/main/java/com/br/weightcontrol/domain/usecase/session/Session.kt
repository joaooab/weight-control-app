package com.br.weightcontrol.domain.usecase.session

import com.br.weightcontrol.model.User

data class Session(
    val user: User = User()
)
