package com.br.weightcontrol.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.data.DashboardWeight
import com.br.weightcontrol.data.goal.Goal
import com.br.weightcontrol.data.goal.GoalRepository
import com.br.weightcontrol.data.weight.Weight
import com.br.weightcontrol.data.weight.WeightRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class DashboardViewModel(private val weightRepository: WeightRepository, private val goalRepository: GoalRepository) : ViewModel() {

    private val _weights = MutableLiveData<List<Weight>>()
    val weights: LiveData<List<Weight>> = _weights
    private val _dashboardWeight = MutableLiveData<DashboardWeight>()
    val dashboardWeight: LiveData<DashboardWeight> = _dashboardWeight
    private val _goal = MutableLiveData<List<Goal>>()
    val goal: LiveData<List<Goal>> = _goal
    val isLoading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<String>()

    init {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val dashboardWeight = createDashboardWeight()
                _dashboardWeight.value = dashboardWeight
                _goal.value = goalRepository.get()
            } catch (e: Exception) {
                onError.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }

    private suspend fun createDashboardWeight(): DashboardWeight {
        val list = weightRepository.get()
        _weights.value = weightRepository.get().sortedByDescending { it.date }
        val maxWight = list.maxBy { it.weight }
        val minWight = list.minBy { it.weight }
        val weightsDescending = weightRepository.get().sortedBy { it.date }
        val dashboardWeight = DashboardWeight(weightsDescending, maxWight, minWight)
        return dashboardWeight
    }
}