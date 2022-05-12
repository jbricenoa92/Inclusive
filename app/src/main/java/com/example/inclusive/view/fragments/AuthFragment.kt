package com.example.inclusive.view.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.inclusive.R
import com.example.inclusive.viewmodel.auth.AuthViewModel
import com.google.firebase.auth.FirebaseUser
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class AuthFragment : Fragment() {

    private lateinit var auth_emailt:EditText
    private lateinit var auth_Password:EditText
    private lateinit var button_ingresar:Button
    private lateinit var button_registrar:Button
    private lateinit var viewModel:AuthViewModel
    private lateinit var navController:NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    try {
        Log.e(TAG, "returnInflater")
        return inflater.inflate(R.layout.fragment_auth, container, false )
    }catch (e:Exception ){
        Log.e(TAG, "onCreateView", e)
        throw e
    }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_emailt= requireView().findViewById(R.id.auth_email) as EditText
        auth_Password= requireView().findViewById(R.id.auth_Password) as EditText
        button_ingresar= requireView().findViewById(R.id.button_ingresar) as Button
        button_registrar= requireView().findViewById(R.id.button_registrar) as Button
        val appContext = requireContext().applicationContext
        navController=Navigation.findNavController(view)

        button_ingresar.setOnClickListener(View.OnClickListener {
            var authemailt=auth_emailt.text.toString()
            var authPassword=auth_Password.text.toString()
              if(authemailt.isNotEmpty() && authPassword.isNotEmpty()){

                  viewModel.sigin(authemailt,authPassword,view.context)
              }else{
                  showAlert("Campos vacios",appContext)
              }
        })

        button_registrar.setOnClickListener(View.OnClickListener {
            var authemailt=auth_emailt.text.toString()
            var authPassword=auth_Password.text.toString()
            if(authemailt.isNotEmpty() && authPassword.isNotEmpty()){

                viewModel.register(authemailt,authPassword,view.context)
            }else{
                showAlert("Campos vacios",appContext)
            }
        })
    }



    private  fun showAlert(message:String,appContext:Context){
        val builder= AlertDialog.Builder(appContext)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }



}