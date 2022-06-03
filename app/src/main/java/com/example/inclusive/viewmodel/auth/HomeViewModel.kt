package com.example.inclusive.viewmodel.auth

import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomeViewModel:ViewModel() {



    private val _selectedItem1= MutableLiveData<Int>()
    val selectedItem1: MutableLiveData<Int>
        get() = _selectedItem1

    private val _selectedItem2= MutableLiveData<Int>()
    val selectedItem2: MutableLiveData<Int>
        get() = _selectedItem2


    fun setitem1(item: Int){
        _selectedItem1.postValue(item)
    }
    fun setItem2(item:Int){
        _selectedItem2.postValue(item)
    }
}


