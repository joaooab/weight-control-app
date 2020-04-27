package com.br.weightcontrol.util

import android.widget.EditText
import com.br.weightcontrol.R

class ValidatorBuilder {

    private var succes: Boolean = true

    fun isRequired(field: EditText): ValidatorBuilder {
        if (field.text.isNullOrEmpty()) {
            field.error = LayoutUtil.getString(R.string.field_required)
            succes = false
        }

        return this
    }

    fun isBetween(field: EditText, min: Int, max: Int): ValidatorBuilder {
        val text = field.text.toString()
        if (text.isEmpty()) return this
        val value = text.toInt()
        if (value !in min..max) {
            field.error = LayoutUtil.getString(R.string.field_invalid_between_value)
            succes = false
        }

        return this
    }

    fun build(): Boolean = succes
}