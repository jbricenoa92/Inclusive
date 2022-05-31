package com.example.inclusive.model.repository

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FirebaseRepository {

    private var _firebasestoreMutable=MutableLiveData<Boolean>()
        var firebasestoreMutable:MutableLiveData<Boolean>
        get() = _firebasestoreMutable

    var storage = Firebase.storage
    val storageRef = storage.reference


    constructor(){
        this.firebasestoreMutable=MutableLiveData()
    }

    fun uploadtoFirebase (imagen:Uri,name:String,context: Context){

        if(imagen !=null && name !=null){
            val mountainImagesRef = storageRef.child("signals/$name")
            var uploadtask=mountainImagesRef.putFile(imagen)
            uploadtask.addOnFailureListener{
                _firebasestoreMutable.postValue(false)
                Toast.makeText(context, "Error en carga",
                    Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                _firebasestoreMutable.postValue(true)
                Toast.makeText(context, "Carga correcta",
                    Toast.LENGTH_SHORT).show()
            }

        }else{

            Toast.makeText(context, "Campos vacios.",
                Toast.LENGTH_SHORT).show()
        }



    }
}