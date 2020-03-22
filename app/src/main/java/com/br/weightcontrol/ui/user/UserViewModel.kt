package com.br.weightcontrol.ui.user

import androidx.lifecycle.ViewModel
import com.br.weightcontrol.data.user.User
import com.br.weightcontrol.util.PreferencesHelper
import com.br.weightcontrol.util.PreferencesHelper.Companion.PREFS_USER
import com.google.gson.Gson

class UserViewModel(val preferencesHelper: PreferencesHelper) : ViewModel() {

    fun save(user: User) {
        val json = Gson().toJson(user)
        preferencesHelper.save(PREFS_USER, json)
    }

}