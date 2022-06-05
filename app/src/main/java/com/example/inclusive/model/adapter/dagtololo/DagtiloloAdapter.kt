package com.example.inclusive.model.adapter.dagtololo

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inclusive.R
import com.example.inclusive.model.provider.dagtilolo.Dagtilolo

class DagtiloloAdapter (val listesDagtilolo:MutableList<Dagtilolo>): RecyclerView.Adapter<DagtiloloViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DagtiloloViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return DagtiloloViewHolder(layoutInflater.inflate(R.layout.item_dagtilo,parent,false))
    }

    override fun onBindViewHolder(holder: DagtiloloViewHolder, position: Int) {
        val item=listesDagtilolo[position]
        holder.render(item)
    }

    override fun getItemCount(): Int =listesDagtilolo.size
}