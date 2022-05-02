package com.example.inclusive.viewmodel.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inclusive.model.user.UserModel

class UserViewModel:ViewModel() {

    val userModel=MutableLiveData<UserModel>()
}