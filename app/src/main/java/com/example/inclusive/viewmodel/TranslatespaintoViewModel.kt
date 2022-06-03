package com.example.inclusive.viewmodel

import android.util.Log
import android.view.KeyEvent
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inclusive.model.provider.braille.Braille
import com.example.inclusive.model.provider.braille.BrailleProvider
import com.example.inclusive.model.provider.braille.BrailleProvider.Companion.brailleList
import org.w3c.dom.Text

class TranslatespaintoViewModel:ViewModel() {

    private val _obtenerMutable= MutableLiveData<String>()
    val obtenerMutable:MutableLiveData<String>
    get() = _obtenerMutable


    fun setObtener(texto:String){

        if(texto !=null){
            for(char in texto){

                if(char.toString()==" "){
                    Log.e("char",char.toString())
                }else{

                    var  braille= Braille(char.toString(),char.toString())
                    brailleList.add(braille)
                }

            }
            _obtenerMutable.postValue(texto)
        }
    }

}


