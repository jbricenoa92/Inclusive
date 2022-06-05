package com.example.inclusive.model.repository


import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.inclusive.model.provider.dagtilolo.Dagtilolo
import com.example.inclusive.model.provider.dagtilolo.DagtiloloProvider.Companion.datiloloList
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.ktx.storage
import com.google.protobuf.Parser
import java.io.File
class FirebaseRepository {

    private var _firebasestoreMutable=MutableLiveData<Boolean>()
        var firebasestoreMutable:MutableLiveData<Boolean>
        get() = _firebasestoreMutable

    private var _fireMutabledownload=MutableLiveData<String>()
    var fireMutabledownload:MutableLiveData<String>
        get() =_fireMutabledownload


    var storage = Firebase.storage
    val storageRef = storage.reference


    constructor(){

        this.firebasestoreMutable=MutableLiveData()
        this.fireMutabledownload= MutableLiveData()
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

    fun descargaFirebase( name:String,context: Context){

       val islandRef= storageRef.child("signals/$name")
        val localFile = File.createTempFile("images", "jpg")
        islandRef.downloadUrl
            .addOnSuccessListener {
                if(it !=null){


                    val dagtil=Dagtilolo(it.toString(),name)
                    datiloloList.add(dagtil)
                    _fireMutabledownload.postValue(it.toString())
                }


                  }
            .addOnCanceledListener{
                Toast.makeText(context, "No existe el archivo",
                    Toast.LENGTH_SHORT).show()
            }

    }
}