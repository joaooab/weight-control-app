package com.br.weightcontrol.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.Weight
import com.br.weightcontrol.extension.formatToString
import com.br.weightcontrol.repository.WeightRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class HomeViewModel(private val weightRepository: WeightRepository) : ViewModel() {

    private val _weight = MutableLiveData<Weight>()
    val weight: LiveData<Weight> = _weight

    init {
        viewModelScope.launch {
            _weight.value = weightRepository.getByDate(Calendar.getInstance().formatToString())
        }
    }

    fun addWeight(weight: Weight) {
        viewModelScope.launch {
            weightRepository.insert(weight)
        }
        _weight.value = weight
    }

    fun getLast() = runBlocking {
        //TODO get async
        val async = async { weightRepository.getLast() }
    }

}
