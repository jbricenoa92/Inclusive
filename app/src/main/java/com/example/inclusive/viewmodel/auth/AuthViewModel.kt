package com.example.inclusive.viewmodel.auth

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inclusive.model.auth.AuthModel
import com.example.inclusive.model.auth.AuthProvide
import com.example.inclusive.view.activities.Activity_auth


class AuthViewModel:ViewModel() {


    val authModel= MutableLiveData<AuthModel>()

    private fun getAuthViewModel(correo:String,contraseña:String){
        val getauthprovider:AuthModel=AuthProvide.getAuthModel(correo,contraseña)
        authModel.postValue(getauthprovider)
      }

}