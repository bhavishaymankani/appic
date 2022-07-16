package com.bhavishaymankani.machinetestappic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishaymankani.machinetestappic.utils.DataRepo
import com.bhavishaymankani.machinetestappic.datasource.model.Data
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {
    private var _data = MutableLiveData<Data>()
    val data: LiveData<Data> = _data

    fun getData() = viewModelScope.launch {
        DataRepo.getData().let {
            _data.postValue(it.body())
        }
    }
}