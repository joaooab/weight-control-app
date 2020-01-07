package com.br.weightcontrol.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.weightcontrol.data.Weight

class HomeViewModel : ViewModel() {

    private val _weight = MutableLiveData<Weight>()
    val weight: LiveData<Weight> = _weight

    fun addWeight(weight: Weight) {
        _weight.value = weight
    }
}
