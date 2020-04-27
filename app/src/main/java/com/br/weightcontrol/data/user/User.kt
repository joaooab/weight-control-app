package com.br.weightcontrol.data.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String,
    val age: Int,
    val gender: String,
    val height: Int
) : Parcelable {
    companion object {
        const val MALE = "M"
        const val FEMALE = "F"
    }
}