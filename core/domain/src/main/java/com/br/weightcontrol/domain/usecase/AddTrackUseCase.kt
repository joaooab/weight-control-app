package com.br.weightcontrol.domain.usecase

import com.br.weightcontrol.model.Track

interface AddTrackUseCase {

    suspend operator fun invoke(track: Track)
}