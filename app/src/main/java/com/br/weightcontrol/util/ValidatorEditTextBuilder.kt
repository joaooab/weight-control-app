package com.br.weightcontrol.util

import android.widget.EditText
import com.br.weightcontrol.R
import com.br.weightcontrol.extension.parseDouble

class ValidadorBuilder {

    private var succes: Boolean = true

    fun isRequired(field: EditText): ValidadorBuilder {
        if (field.text.isNullOrEmpty()) {
            field.error = LayoutUtil.getString(R.string.field_required)
            succes = false
        }

        return this
    }

    fun isBetween(field: EditText, min: Double, max: Double): ValidadorBuilder {
        val value = field.text.toString().parseDouble()
        if (value !in min..max) {
            field.error = LayoutUtil.getString(R.string.field_invalid_between_value)
            succes = false
        }

        return this
    }

    fun build(): Boolean = succes
}