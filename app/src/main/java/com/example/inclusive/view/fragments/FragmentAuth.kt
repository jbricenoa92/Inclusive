package com.example.inclusive.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.inclusive.R
import com.example.inclusive.databinding.FragmentAuth2Binding
import com.example.inclusive.viewmodel.auth.AuthViewModel
import com.google.firebase.auth.FirebaseUser


class FragmentAuth : Fragment() {

    private lateinit var binding: FragmentAuth2Binding
    private lateinit var auth_emailt:EditText
    private lateinit var auth_Password:EditText
    private lateinit var button_ingresar:Button
    private lateinit var button_registrar:Button
    private val viewModel:AuthViewModel by activityViewModels()
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {


        }catch (e:Exception){
            Log.e("error null",e.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        // Inflate the layout for this fragment
        val viewinflate= inflater.inflate(R.layout.fragment_auth2, container, false)

        return viewinflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_emailt= view.findViewById(R.id.auth_email) as EditText
        auth_Password= view.findViewById(R.id.auth_Password) as EditText
        button_ingresar= view.findViewById(R.id.button_ingresar) as Button
        button_registrar= view.findViewById(R.id.button_registrar) as Button
        navController= NavController(view.context)

       if(view !=null){
            viewModel.getuserData.observe(viewLifecycleOwner,Observer<FirebaseUser>{firebaseUser->
                var authemailt=auth_emailt.text.toString()
                if(firebaseUser.email==authemailt){
                    Log.e("observer2",firebaseUser.email.toString())
                    Log.e("observer2",firebaseUser.toString())
                    val fragment = HomeFragment()
                    val transaction =  fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.fragmentContainerView2, fragment)?.commit()
            }
           })
        }

        button_ingresar.setOnClickListener(View.OnClickListener {


            var authemailt=auth_emailt.text.toString()
            var authPassword=auth_Password.text.toString()

            if(authemailt.isNotEmpty() && authPassword.isNotEmpty()){

               viewModel.sigin(authemailt,authPassword,view.context)


            }else{
                showAlert("Campos vacios",view.context )
            }
        })

        button_registrar.setOnClickListener(View.OnClickListener {
            var authemailt=auth_emailt.text.toString()
            var authPassword=auth_Password.text.toString()
            if(authemailt.isNotEmpty() && authPassword.isNotEmpty()){
                Log.e("registrarLogin",auth_emailt.toString())
              var ingresar=  viewModel.register(authemailt,authPassword,view.context)
                Log.e("registrarLogin",ingresar.toString())
            }else{
                showAlert("Campos vacios",view.context)
            }
        })
    }
    override fun onDestroyView(){
    viewModel.getuserData.removeObservers(viewLifecycleOwner)
    super.onDestroyView()
    }
    private  fun showAlert(message:String,appContext: Context){
        val builder= AlertDialog.Builder(appContext)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
}



