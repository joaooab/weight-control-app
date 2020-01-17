package com.br.weightcontrol.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.Weight
import com.br.weightcontrol.repository.WeightRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val weightRepository: WeightRepository) : ViewModel() {

	private val _weights = MutableLiveData<List<Weight>>()
	val weights: LiveData<List<Weight>> = _weights

    init {
        viewModelScope.launch {
            _weights.value = weightRepository.get()
        }
    }
}