package com.br.weightcontrol

import androidx.room.TypeConverter
import com.br.weightcontrol.extension.formatToString
import com.br.weightcontrol.extension.toCalendar
import java.util.*

class Converters {
    @TypeConverter
    fun fromCalendar(value: String): Calendar? {
        return value.toCalendar()
    }

    @TypeConverter
    fun calendarToString(value: Calendar?): String? {
        return value?.formatToString()
    }
}
