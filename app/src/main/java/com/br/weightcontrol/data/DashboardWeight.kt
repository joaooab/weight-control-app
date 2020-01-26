package com.br.weightcontrol.data

import com.br.weightcontrol.data.weight.Weight

data class DashboardWeight(
    val weights: List<Weight>,
    val maxWeight: Weight?,
    val minWeight: Weight?
)