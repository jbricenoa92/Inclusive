package com.example.inclusive.model.adapter.firebaseimage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inclusive.R
import com.example.inclusive.model.provider.firebaseimage.Imagefirebase

class FirebaseImageAdapter(private val imageFirebase: List<Imagefirebase>):RecyclerView.Adapter<FirebaseImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirebaseImageViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        return FirebaseImageViewHolder(layoutInflater.inflate(R.layout.item_imagefirebase,parent,false))
    }

    override fun onBindViewHolder(holder: FirebaseImageViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount()=imageFirebase.size
}