package com.example.inclusive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.inclusive.R
import com.google.firebase.auth.FirebaseAuth
import java.security.Provider

class Activity_auth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setUp()
    }

    //Check text fields
    fun setUp(){

        var button_ingresar=findViewById<Button>(R.id.button_ingresar)
        var button_registrar=findViewById<Button>(R.id.button_registrar)
        var auth_email:EditText=findViewById(R.id.auth_email)
        var auth_Password:EditText=findViewById(R.id.auth_Password)


        button_registrar.setOnClickListener{
            val activity_auth=Intent(this,Activity_register::class.java)
            startActivity(activity_auth)
        }

        button_ingresar.setOnClickListener{
            if (auth_email.text.isNotEmpty() && auth_Password.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(auth_email.text.toString(),auth_Password.text.toString())
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