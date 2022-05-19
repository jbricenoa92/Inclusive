package com.example.inclusive.viewmodel.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inclusive.model.repository.ImageRepository
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class UploadViewModel: ViewModel {

        private var imageRepository:ImageRepository

        private var _uploadMutable:MutableLiveData<Boolean>
        val uploadMutable:MutableLiveData<Boolean>
            get() =_uploadMutable

    private val file=1
    private val database= Firebase.storage
    private val ref= database.reference


        constructor(){
            imageRepository= ImageRepository()
            this._uploadMutable=imageRepository.firebasestoreMutable
        }


    fun getimage(activity: Activity, bundle: Bundle?){

        if(bundle!=null){
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            ActivityCompat.startActivityForResult(activity, intent, file, bundle)
        }
    }


}