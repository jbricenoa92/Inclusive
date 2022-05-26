package com.example.inclusive.viewmodel.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomeViewModel:ViewModel() {



    private val _selectedItem= MutableLiveData<Int>().apply {

    }
    val selectedItem: MutableLiveData<Int>
        get() = _selectedItem


    fun setData(item:Int){
        _selectedItem.postValue(item)
    }
}


