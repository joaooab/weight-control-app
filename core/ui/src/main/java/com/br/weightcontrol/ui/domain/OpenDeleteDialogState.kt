package com.br.weightcontrol.ui.domain

sealed interface DeleteDialogState<T> {
    class Open<T>(val data: T) : DeleteDialogState<T>
    class Hide<T> : DeleteDialogState<T>
}
