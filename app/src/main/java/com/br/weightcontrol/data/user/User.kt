package com.br.weightcontrol.data.user

data class User(val name: String, val age: Int, val gender: String) {
    companion object {
        const val MALE = "M"
        const val FEMALE = "F"
    }
}