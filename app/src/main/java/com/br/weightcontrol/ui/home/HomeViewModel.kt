package com.br.weightcontrol.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.weightcontrol.R
import com.br.weightcontrol.data.goal.Goal
import com.br.weightcontrol.data.goal.GoalRepository
import com.br.weightcontrol.data.goal.GoalWithWeight
import com.br.weightcontrol.data.weight.Weight
import com.br.weightcontrol.data.weight.WeightRepository
import com.br.weightcontrol.extension.formatToString
import com.br.weightcontrol.util.LayoutUtil
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
    private val weightRepository: WeightRepository,
    private val goalRepository: GoalRepository
) : ViewModel() {

    private val _weight = MutableLiveData<Weight>()
    val weight: LiveData<Weight> = _weight
    private val _lastWeight = MutableLiveData<Weight>()
    val lastWeight: LiveData<Weight> = _lastWeight
    private val _goal = MutableLiveData<GoalWithWeight>()
    val goal: LiveData<GoalWithWeight> = _goal
    val onError = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            _weight.value = weightRepository.getByDate(Calendar.getInstance().formatToString())
            _lastWeight.value = weightRepository.getLast()
            if (_lastWeight.value != null) {
                val lastGoal = goalRepository.getLast()
                val goalWithWeight = GoalWithWeight(lastGoal, _lastWeight.value!!)
                _goal.value = goalWithWeight
            }
        }
    }

    fun addWeight(weight: Weight) {
        viewModelScope.launch {
            weightRepository.insert(weight)
        }
        _weight.value = weight
    }

    fun getLast(): Weight? {
        return lastWeight.value
    }

    fun addGoal(current: Weight, end: Weight) {
        viewModelScope.launch {
            if (current.weight <= end.weight) {
                onError.value = LayoutUtil.getString(R.string.error_weight_greater_than_goal)
            } else {
                val goal = Goal(begin = current.weight, end = end.weight)
                goalRepository.insert(goal)
            }
        }
    }

}
