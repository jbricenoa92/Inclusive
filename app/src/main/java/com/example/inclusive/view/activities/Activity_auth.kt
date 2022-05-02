package com.example.inclusive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.inclusive.R
import com.example.inclusive.databinding.ActivityAuthBinding
import com.example.inclusive.viewmodel.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class Activity_auth : AppCompatActivity() {

    //Binding
    private lateinit var binding:ActivityAuthBinding
    private val authViewMode: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //authViewMode.

        setUp()
    }

    //Check text fields
    fun setUp(){

        var buttonIngresar=findViewById<Button>(R.id.button_ingresar)
        var buttonRegistrar=findViewById<Button>(R.id.button_registrar)
        var authEmail:EditText=findViewById(R.id.auth_email)
        var authPassword:EditText=findViewById(R.id.auth_Password)


        buttonRegistrar.setOnClickListener{
            val activity_auth=Intent(this,Activity_register::class.java)
            startActivity(activity_auth)
        }

        buttonIngresar.setOnClickListener{
            if (authEmail.text.isNotEmpty() && authPassword.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(authEmail.text.toString(),authPassword.text.toString())
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            show_home(it.result?.user?.email?:"",ProviderType.BASIC)
                        }else{
                            showAlert("Error en autenticacion")
                        }
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

    private fun show_register(){
        val activity_auth=Intent(this,Activity_register::class.java)
        startActivity(activity_auth)
    }
}