package com.br.weightcontrol.data.mapper

import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.GoalEntity

fun List<GoalEntity>.toModel() = map { it.toModel() }

fun GoalEntity.toModel() = Goal(
    id = id,
    start = start,
    desire = desire,
    current = current,
    createdAt = createdAt,
    completedAt = completedAt,
)

fun Goal.toEntity() = GoalEntity(
    id = id,
    start = start,
    desire = desire,
    current = current,
    createdAt = createdAt,
    completedAt = completedAt,
)