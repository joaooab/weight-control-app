package com.br.weightcontrol.extension

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss"
const val DATE_FORMAT_BR = "dd/MM/yyyy"
const val DATE_FORMAT_US = "yyyy-MM-dd"
const val DATE_TIME_FORMAT_BR = "dd/MM/yyyy HH:mm"

fun Calendar.formatDataBr(): String {
    val simpleDateFormat = SimpleDateFormat(DATE_FORMAT_BR, Locale("pt", "BR"))
    return try {
        simpleDateFormat.format(time)
    } catch (e: Exception) {
        Log.e("Extension", "Formato de data inválido")
        ""
    }
}
