package com.example.inclusive.model.adapter.braille

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inclusive.R
import com.example.inclusive.model.provider.braille.Braille

class BrailleViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val braillebrailleletra=view.findViewById<TextView>(R.id.item_braile_letra)
    val brailleSimbol=view.findViewById<TextView>(R.id.item_braille_simbol)
    fun render(brailleModel:Braille){
        braillebrailleletra.text=brailleModel.letra
        brailleSimbol.text=brailleModel.simbolo
    }
}