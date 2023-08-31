package com.br.weightcontrol.ui.input

import android.os.Parcelable
import androidx.annotation.StringRes

interface InputHandler: Parcelable {

    val input: String

    @Throws(Exception::class)
    fun validate()

    @StringRes
    fun error(): Int?

    fun isValid() = runCatching { validate() }.isSuccess
}