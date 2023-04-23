package com.br.weightcontrol.history.domain.model

import com.br.weightcontrol.model.Track

sealed interface DeleteDialogState {
    class Open(val track: Track) : DeleteDialogState
    object Hide : DeleteDialogState
}
