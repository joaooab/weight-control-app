package com.br.weightcontrol.ui.chart

import com.patrykandpatrick.vico.core.entry.ChartEntry
import kotlinx.datetime.LocalDate

internal class Entry(
    val localDate: LocalDate,
    override val x: Float,
    override val y: Float,
) : ChartEntry {
    override fun withY(y: Float) = Entry(localDate, x, y)
}
