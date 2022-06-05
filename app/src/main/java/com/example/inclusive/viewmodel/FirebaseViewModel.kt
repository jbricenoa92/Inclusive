package com.example.inclusive.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inclusive.model.repository.FirebaseRepository
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FirebaseViewModel: ViewModel {

        private val file=1
        private var firebaseRepository:FirebaseRepository

        private var _downloadMutable:MutableLiveData<String>
        val downloadMutable:MutableLiveData<String>
            get() =_downloadMutable

    private var _uploadMutable:MutableLiveData<Boolean>
    val uploadMutable:MutableLiveData<Boolean>
        get() =_uploadMutable

        constructor(){
            firebaseRepository= FirebaseRepository()
            this._uploadMutable=firebaseRepository.firebasestoreMutable
            this._downloadMutable=firebaseRepository.fireMutabledownload
        }

    fun downloadimage(name:String,context: Context){
        if(name!=null && context !=null){
        firebaseRepository.descargaFirebase(name,context)

        }
    }

    fun uploadimage(image: Uri?,name:String,context: Context){
        if (image != null) {
            firebaseRepository.uploadtoFirebase(image,name,context)
        }
    }


}