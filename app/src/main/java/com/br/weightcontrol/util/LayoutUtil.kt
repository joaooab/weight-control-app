package com.br.weightcontrol.util

import android.content.Context
import androidx.core.content.ContextCompat
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

object LayoutUtil : KoinComponent {

    private val context: Context by inject()

    fun getColor(color: Int) = ContextCompat.getColor(context, color)

    fun getColorStateList(color: Int) = ContextCompat.getColorStateList(context, color)

    fun getDrawable(drawable: Int) = ContextCompat.getDrawable(context, drawable)

    fun getString(idString: Int): String = context.getString(idString)
}
