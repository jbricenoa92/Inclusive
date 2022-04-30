package com.example.inclusive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.inclusive.R
import com.google.firebase.auth.FirebaseAuth

class Activity_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setUp()
    }


    fun setUp(){

        val registerName:EditText=findViewById(R.id.register_name)
        val authEmail: EditText =findViewById(R.id.register_email)
        val registerPassword:EditText=findViewById(R.id.register_password)
        var authPassword: EditText =findViewById(R.id.auth_Password)
        var confirmRegister_button:EditText=findViewById(R.id.register_confirm_password)
        val buttonRegistrar: Button =findViewById(R.id.confirm_register_button)

        buttonRegistrar.setOnClickListener{
            if (authEmail.text.isNotEmpty() && authPassword.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(authEmail.text.toString(),authPassword.text.toString())
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
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

    private fun show_home(email:String,provider:ProviderType){
        val homeIntent= Intent(this,MainActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)
    }

}