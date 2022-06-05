package com.example.inclusive.model.adapter.translate

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inclusive.R
import com.example.inclusive.model.provider.espaol.Espaol

class EspaolViewHolder(view: View):RecyclerView.ViewHolder(view)  {

    val espaol=view.findViewById<TextView>(R.id.item_spaol)
    fun render(espaolModel:Espaol){
        espaol.text=espaolModel.simbol
    }

}