package com.example.inclusive.model.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthRepository {

     private var _firebaseUserMutableLiveData= MutableLiveData<FirebaseUser>()
     var firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>
     get()=_firebaseUserMutableLiveData

    private var _userLoggedMutableLiveData= MutableLiveData<Boolean>()
    val userLoggedMutableLiveData:MutableLiveData<Boolean>
    get()=_userLoggedMutableLiveData


   // private var application:Application
    private var auth: FirebaseAuth

   constructor()  {
    //   this.application=application
       this.firebaseUserMutableLiveData = MutableLiveData()
       this._userLoggedMutableLiveData= MutableLiveData()

       auth= FirebaseAuth.getInstance()

       if(auth.currentUser != null){
           _firebaseUserMutableLiveData.postValue(auth.currentUser)
       }
   }
// registro a la aplicacion
    fun register(correo:String,contraseña:String,context: Context) {
        auth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener{
        task ->

            if(task.isSuccessful){
                _firebaseUserMutableLiveData.postValue(auth.currentUser)

            }else{
              Toast.makeText(context, "Regiter failed.",
                Toast.LENGTH_SHORT).show()

            }
        }
        }
//Login de usuario ya existente
    fun sigin(correo:String,contraseña:String,context: Context){
        auth.signInWithEmailAndPassword(correo, contraseña)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    _firebaseUserMutableLiveData.postValue(auth.currentUser)
                } else {
                    // If sign in fails, display a message to the user.
                   Toast.makeText(context, "Authentication failed.",
                       Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun logout(){
        Firebase.auth.signOut()
          _userLoggedMutableLiveData.postValue(true)
    }


    }


