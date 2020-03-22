package com.br.weightcontrol.util

import android.content.Context
import com.br.weightcontrol.data.user.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferencesHelper(private val context: Context) {

    companion object {
        const val PREFS_WEIGHT_CONRTROL = "PREFS_WEIGHT_CONRTROL"
        const val PREFS_USER = "PREFS_WEIGHT_CONRTROL"
    }

    private val gson = Gson()

    fun save(key: String, value: String) {
        getEditor()
            .putString(key, value)
            .apply()
    }

    fun getUser(key: String = PREFS_USER, default: String = ""): User? {
        val value = getPreferences()
            .getString(key, default)
        if (value == default) return null
        val type = object : TypeToken<User>() {}.type
        return gson.fromJson(value, type)
    }

    private fun getEditor() = getPreferences().edit()

    private fun getPreferences() = context
        .getSharedPreferences(PREFS_WEIGHT_CONRTROL, Context.MODE_PRIVATE)
}