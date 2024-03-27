package com.br.weightcontrol.model

sealed interface ActionState {
    object Start : ActionState
    object Success : ActionState
    object Failure : ActionState
}