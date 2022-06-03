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

        private var firebaseRepository:FirebaseRepository

        private var _uploadMutable:MutableLiveData<Boolean>
        val uploadMutable:MutableLiveData<Boolean>
            get() =_uploadMutable

    private val file=1
    private val database= Firebase.storage
    private val ref= database.reference



        constructor(){
            firebaseRepository= FirebaseRepository()
            this._uploadMutable=firebaseRepository.firebasestoreMutable
        }

    fun getimage(activity: Activity, bundle: Bundle?){

        if(bundle!=null){
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            ActivityCompat.startActivityForResult(activity, intent, file, bundle)
        }
    }

    fun uploadimage(image: Uri?,name:String,context: Context){
        if (image != null) {
            firebaseRepository.uploadtoFirebase(image,name,context)
        }
    }


}