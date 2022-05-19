package com.example.inclusive.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.inclusive.R
import com.example.inclusive.viewmodel.auth.AuthViewModel
import com.example.inclusive.viewmodel.auth.HomeViewModel
import com.google.firebase.storage.FirebaseStorage

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spLista: Spinner = view.findViewById(R.id.spinner_1)
        val spLista2: Spinner = view.findViewById(R.id.spinner_2)
        val imageView=view.findViewById<ImageView>(R.id.showsignal)
        val edittext: EditText =view.findViewById(R.id.getText_home)
        val storage= FirebaseStorage.getInstance().getReference()
        val ONE_MEGABYTE: Long = 1024 * 1024


        edittext.setOnKeyListener(View.OnKeyListener
        {
                v, keyCode, event -> if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val getSpinner1:String= spLista.selectedItem.toString() as String
            val getSpinner2:String= spLista2.selectedItem.toString() as String

            Log.e("Spinner1",getSpinner1)
            Log.e("Spinner2",getSpinner2)
        }
            false
        })


        val adp= ArrayAdapter.createFromResource(this.requireContext(),R.array.Lista,android.R.layout.simple_spinner_item)
            .also{adapter->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spLista.adapter = adapter
                spLista2.adapter = adapter
            }
    }
    companion object {
        fun newInstance() = HomeFragment()
    }

    }