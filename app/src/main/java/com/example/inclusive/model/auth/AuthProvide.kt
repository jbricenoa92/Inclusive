package com.example.inclusive.model.auth


import com.google.firebase.auth.FirebaseAuth

class AuthProvide {
    companion object{
        fun getAuthModel(correo:String,contraseña:String,access:Boolean):AuthModel{
           // Log.d("getprovider1", au.estate.toString())
           var au=AuthModel(correo,contraseña,true)
          return au

        }
        fun getauthProvider(correo:String,contraseña:String){
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(correo,contraseña)
                .addOnCompleteListener{

                if( it.isSuccessful){
                    getAuthModel(correo,contraseña,true)
                }
                }
        }

    }

}