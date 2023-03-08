package com.br.weightcontrol.data.mapper

import com.br.weightcontrol.model.Track
import com.br.weightcontrol.model.TrackEntity

fun List<TrackEntity>.toModel() = map { it.toModel() }

fun TrackEntity.toModel() = Track(
    id = id,
    weight = weight,
    createdAt = createdAt,
)

fun Track.toEntity() = TrackEntity(
    id = id,
    weight = weight,
    createdAt = createdAt,
)
