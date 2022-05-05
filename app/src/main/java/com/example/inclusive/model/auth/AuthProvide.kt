package com.example.inclusive.model.auth

import com.google.firebase.auth.FirebaseAuth

class AuthProvide {
    companion object{

        fun getAuthModel(correo:String,contraseña:String):AuthModel{

            val au:AuthModel=AuthModel(correo,contraseña, getauthProvider())

          return au

        }
private fun getauthProvider():Boolean{
            var result:Boolean=true

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword("authEmail.text.toString()","authPassword.text.toString()")
                .addOnCompleteListener{
                    if(it.isSuccessful){

                    }else{

                    }
                }
            return result
        }

    }

}