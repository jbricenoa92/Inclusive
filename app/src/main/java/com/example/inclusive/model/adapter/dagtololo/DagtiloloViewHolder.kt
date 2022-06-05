package com.example.inclusive.model.adapter.dagtololo

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inclusive.R
import com.example.inclusive.model.provider.dagtilolo.Dagtilolo
import kotlinx.android.synthetic.main.fragment_auth.view.*

class DagtiloloViewHolder (view: View): RecyclerView.ViewHolder(view) {

    val dagtiloloImage=view.findViewById<ImageView>(R.id.item_image_dagtilolo)
    val dagtiloloname=view.findViewById<TextView>(R.id.item_name_dagtilolo)
    fun render(dagtiloloModel:Dagtilolo){

        Glide.with(dagtiloloImage.context).load(dagtiloloModel.image).into(dagtiloloImage)
        dagtiloloname.text=dagtiloloModel.name
    }
}