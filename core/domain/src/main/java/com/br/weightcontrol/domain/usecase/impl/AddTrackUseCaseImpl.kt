package com.br.weightcontrol.domain.usecase.impl

import com.br.weightcontrol.data.repository.GoalRepository
import com.br.weightcontrol.data.repository.TrackRepository
import com.br.weightcontrol.domain.usecase.AddTrackUseCase
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.util.todayAsString

class AddTrackUseCaseImpl(
    private val trackRepository: TrackRepository,
    private val goalRepository: GoalRepository
) : AddTrackUseCase {

    override suspend fun invoke(track: Track) {
        trackRepository.insert(track)
        val currentGoal = goalRepository.get() ?: return
        if (currentGoal.shouldComplete(track)) goalRepository.update(
            currentGoal.copy(completedAt = todayAsString())
        )
    }
}