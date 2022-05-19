package com.example.inclusive.model.repository

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ImageRepository {

    private var _firebasestoreMutable=MutableLiveData<Boolean>()
        var firebasestoreMutable:MutableLiveData<Boolean>
        get() = _firebasestoreMutable







    constructor(){
        this.firebasestoreMutable=MutableLiveData()
    }



}