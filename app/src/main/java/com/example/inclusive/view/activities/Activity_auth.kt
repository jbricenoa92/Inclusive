package com.example.inclusive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.inclusive.R
import com.example.inclusive.databinding.ActivityAuthBinding
import com.example.inclusive.model.auth.AuthModel
import com.example.inclusive.viewmodel.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class Activity_auth : AppCompatActivity() {

    //Binding
    private lateinit var binding:ActivityAuthBinding
    private val authViewMode: AuthViewModel by viewModels()
    var accessauth:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewMode.authModel.observe(this, Observer {access->
            Log.d("getaccess", access.estate.toString())
            accessauth=access.estate
        })

        setUp()
    }

    //Check text fields
    fun setUp(){

        var buttonIngresar=findViewById<Button>(R.id.button_ingresar)
        var buttonRegistrar=findViewById<Button>(R.id.button_registrar)
        var authEmail:EditText=findViewById(R.id.auth_email)
        var authPassword:EditText=findViewById(R.id.auth_Password)


        binding.buttonRegistrar.setOnClickListener{
            val activity_auth=Intent(this,Activity_register::class.java)
            startActivity(activity_auth)
        }

        binding.buttonIngresar.setOnClickListener{

            if (authEmail.text.isNotEmpty() && authPassword.text.isNotEmpty()){
                authViewMode.getAuthViewModel(authEmail.text.toString(),authPassword.text.toString())
               if(accessauth){
                   show_home(authEmail.text.toString(),ProviderType.BASIC)


               }else{

                   showAlert("Error de autenticacion")
               }

            }else{
                showAlert("Campos vacios")
            }
        }
    }
    private  fun showAlert(message:String){
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }

    private fun show_home(email:String,provider:ProviderType){
        val homeIntent=Intent(this,MainActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)
     }

    private fun show_register(correo:String,contraseña:String){
        val activity_auth=Intent(this,Activity_register::class.java)
        activity_auth.putExtra("correo",correo)
        activity_auth.putExtra("contraseña",contraseña)
        startActivity(activity_auth)
    }
}