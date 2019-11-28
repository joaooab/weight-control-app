package com.br.weightcontrol.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

	private val _weight = MutableLiveData<String>().apply {
		value = "80"
	}
	val weight: LiveData<String> = _weight
}