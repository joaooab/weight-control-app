package com.br.weightcontrol.util

import java.math.BigDecimal
import java.math.RoundingMode

fun Number.toPercent() = "${this.toInt()}%"

fun calculateWithScale(
    newScale: Int = 1,
    roundingMode: RoundingMode = RoundingMode.HALF_UP,
    block: () -> Double
) = BigDecimal(block())
    .setScale(newScale, roundingMode)
    .toDouble()
