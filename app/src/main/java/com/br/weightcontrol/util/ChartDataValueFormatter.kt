package com.br.weightcontrol.util

import com.github.mikephil.charting.formatter.ValueFormatter

class ChartDataValueFormatter(private val dates: MutableList<String>) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return try {
            val date = dates[value.toInt()]
            date.substring(0, 5)
        } catch (e: IndexOutOfBoundsException) {
            ""
        }
    }

}