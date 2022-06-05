package com.example.inclusive.model.adapter.translate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.inclusive.R.*
import com.example.inclusive.model.provider.espaol.Espaol

class EspaolAdapter(val listespaol:MutableList<Espaol>): RecyclerView.Adapter<EspaolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspaolViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        return EspaolViewHolder(layoutInflater.inflate(layout.item_espaol,parent,false))
    }

    override fun onBindViewHolder(holder: EspaolViewHolder, position: Int) {
        val item=listespaol[position]
        holder.render(item)
    }

    override fun getItemCount(): Int =listespaol.size
}