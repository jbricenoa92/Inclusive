package com.example.inclusive.model.adapter.braille

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inclusive.R
import com.example.inclusive.model.provider.braille.Braille

class BrailleAdapter(val listBraille:MutableList<Braille>):RecyclerView.Adapter<BrailleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrailleViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return BrailleViewHolder(layoutInflater.inflate(R.layout.item_braille,parent,false))
    }

    override fun onBindViewHolder(holder: BrailleViewHolder, position: Int) {
       val item=listBraille[position]
        holder.render(item)
    }

    override fun getItemCount()=listBraille.size
}