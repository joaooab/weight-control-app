package com.br.weightcontrol.data.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Session {
    private var _user: MutableLiveData<User> = MutableLiveData()
    var user: LiveData<User> = _user

    fun setUser(user: User?) {
        if (user == null) return
        _user.value = user
    }
}