package com.br.weightcontrol.ui.input

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputWrapper(val input: String = "", val errorId: Int? = null) : Parcelable {

    fun hasError() = errorId != null
}
