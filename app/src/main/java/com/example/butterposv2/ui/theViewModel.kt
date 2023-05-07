package com.example.butterposv2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class theViewModel : ViewModel() {
    private val selectedDataList = MutableLiveData<List<Pair<String, Int>>>()

    fun setSelectedDataList(dataList: MutableLiveData<List<Pair<String, Int>>>) {
        selectedDataList.value = dataList
    }

    fun getSelectedDataList(): LiveData<List<Pair<String, Int>>> {
        return selectedDataList
    }


}
