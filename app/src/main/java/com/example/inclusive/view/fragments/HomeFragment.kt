package com.example.inclusive.view.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.inclusive.R
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

        val spLista: Spinner = view.findViewById(R.id.spinner_1)
        val spLista2: Spinner = view.findViewById(R.id.spinner_2)
        val edittext: TextView = view.findViewById(R.id.getText_home)
        val storage = FirebaseStorage.getInstance().getReference()
        val ONE_MEGABYTE: Long = 1024 * 1024

        edittext.setOnKeyListener(
            View.OnKeyListener
            { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    val getSpinner1: String = spLista.selectedItem.toString()
                    val getSpinner2: String = spLista2.selectedItem.toString()

                }
                false
            })

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.Lista,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spLista.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.Lista,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spLista2.adapter = adapter
        }

        spLista.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                viewModel.setData(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
  }
        companion object {
            fun newInstance() = HomeFragment()
        }


}