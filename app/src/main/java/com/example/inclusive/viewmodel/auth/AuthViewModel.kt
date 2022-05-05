package com.example.inclusive.viewmodel.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inclusive.model.auth.AuthModel
import com.example.inclusive.model.auth.AuthProvide
import com.example.inclusive.view.activities.Activity_auth


class AuthViewModel:ViewModel() {


    val authModel= MutableLiveData<AuthModel>()

    fun getAuthViewModel(correo:String,contraseña:String){
        AuthProvide.getauthProvider(correo,contraseña)
        val getauthprovider:AuthModel=AuthProvide.getAuthModel(correo,contraseña,false)
        Log.d("providemv",getauthprovider.estate.toString())
        authModel.postValue(getauthprovider)

      }






}