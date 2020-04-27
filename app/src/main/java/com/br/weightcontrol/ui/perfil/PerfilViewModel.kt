package com.br.weightcontrol.ui.perfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.goal.GoalRepository
import com.br.weightcontrol.data.user.Session
import com.br.weightcontrol.data.user.User
import com.br.weightcontrol.data.weight.Weight
import com.br.weightcontrol.data.weight.WeightRepository
import kotlinx.coroutines.launch

class PerfilViewModel(weightRepository: WeightRepository, goalRepository: GoalRepository) : ViewModel() {

    val user: LiveData<User> = Session.user
    private val _lastWeight = MutableLiveData<Weight>()
    val lastWeight: LiveData<Weight> = _lastWeight
    private val _firstWeight = MutableLiveData<Weight>()
    val firstWeight: LiveData<Weight> = _firstWeight
    private val _lowerWeight = MutableLiveData<Weight>()
    val lowerWeight: LiveData<Weight> = _lowerWeight
    private val _higherWeight = MutableLiveData<Weight>()
    val higherWeight: LiveData<Weight> = _higherWeight
    private val _totalGoals = MutableLiveData<Int>()
    val totalGoals: LiveData<Int> = _totalGoals
    val onError = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            try {
                _lastWeight.value = weightRepository.getLast()
                _firstWeight.value = weightRepository.getFirst()
                _lowerWeight.value = weightRepository.getLower()
                _higherWeight.value = weightRepository.getHigher()
                _totalGoals.value = goalRepository.getCountFinished()
            } catch (e: Exception) {
                onError.value = e.message
            }
        }
    }

}