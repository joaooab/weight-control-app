package com.br.weightcontrol.ui.home

import android.util.Log
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
import java.lang.Exception
import java.util.*

class HomeViewModel(
    private val weightRepository: WeightRepository,
    private val goalRepository: GoalRepository
) : ViewModel() {

    private val _weight = MutableLiveData<Weight>()
    val weight: LiveData<Weight> = _weight
    private val _lastWeight = MutableLiveData<Weight>()
    private val _goal = MutableLiveData<Goal>()
    val goal: LiveData<Goal> = _goal
    val onError = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            _weight.value = weightRepository.getByDate(Calendar.getInstance().formatToString())
            _lastWeight.value = weightRepository.getLast()
            _goal.value = goalRepository.getLast()
        }
    }

    fun addWeight(weight: Weight) {
        viewModelScope.launch {
            weightRepository.insert(weight)
            updateGoal(weight)
        }
        _weight.value = weight
    }

    private suspend fun updateGoal(weight: Weight) {
        _goal.value?.apply {
            current = weight.weight
        }?.let {
            goalRepository.update(it)
            _goal.postValue(it)
        }
    }

    fun updateGoal(goal: Goal) {
        viewModelScope.launch {
            goalRepository.update(goal)
            _goal.postValue(goal)
        }
    }

    fun getLastWeight(): Weight? {
        return _weight.value ?: _lastWeight.value
    }

    fun addGoal(goal: Goal) {
        viewModelScope.launch {
            if (goal.end >= goal.current) {
                onError.value = LayoutUtil.getString(R.string.error_weight_greater_than_goal)
            } else {
                goalRepository.insert(goal)
                _goal.value = goal
            }
        }
    }

    fun deleteGoal() {
        _goal.value?.let {
            viewModelScope.launch {
                goalRepository.delete(it)
                _goal.postValue(null)
            }
        }
    }

    fun updateWeight(weight: Weight) {
        viewModelScope.launch {
            try {
                val id = _weight.value?.id ?: return@launch
                val newWeight = Weight(id, weight.weight, weight.date)
                weightRepository.update(newWeight)
            } catch (e: Exception) {
                Log.e("e", e.message)
            }
            updateGoal(weight)
            _weight.postValue(weight)
        }
    }

}
