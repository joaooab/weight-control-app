package com.br.weightcontrol.util

import android.widget.EditText
import com.br.weightcontrol.R

class ValidadorBuilder {

    private val MESSAGE_FIELD_REQUIRED = LayoutUtil.getString(R.string.field_required)
    var succes: Boolean = true

    fun isRequired(field: EditText): ValidadorBuilder {
        if (field.text.isNullOrEmpty()) {
            field.error = MESSAGE_FIELD_REQUIRED
            succes = false
        }
        return this
    }

    fun build(): Boolean = succes
}