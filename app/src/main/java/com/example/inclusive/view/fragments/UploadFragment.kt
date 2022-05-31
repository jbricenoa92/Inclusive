package com.example.inclusive.view.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.inclusive.R
import com.example.inclusive.viewmodel.auth.FirebaseViewModel


class upload_Fragment : Fragment() {

        private val firebaseViewModel: FirebaseViewModel by viewModels()
        private lateinit var buttonupload:ImageButton
        private lateinit var buttonsendImage:Button
        private lateinit var cargarname:EditText
        private var data: Uri? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonupload=view.findViewById(R.id.imageViewUpload)
        buttonsendImage=view.findViewById(R.id.buttonSendImages)
        cargarname=view.findViewById(R.id.cargarname)

        if(view!=null){
            firebaseViewModel.uploadMutable.observe(viewLifecycleOwner, Observer{
                val respuesta=firebaseViewModel.uploadMutable.value
                if(firebaseViewModel.uploadMutable.value !=null){
                    if(respuesta==true){
                        buttonupload.setImageResource(R.drawable.upload_foreground)
                        buttonsendImage.text=""

            } }
            })
        }

        buttonupload.setOnClickListener(View.OnClickListener {
            getphoto()
      })

        buttonsendImage.setOnClickListener {

                if( cargarname.text.toString() !=null && cargarname.text.toString() !="" ){
                    firebaseViewModel.uploadimage(data, cargarname.text.toString(),requireContext())


                }else{
                    Toast.makeText(context, "Campos vacios",
                        Toast.LENGTH_SHORT).show()
                }

        }


    }

    fun getphoto(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        starForActivityGalery.launch(intent)

    }

    private val starForActivityGalery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode==Activity.RESULT_OK){
            data = it.data?.data!!
            if(data !=null){
                buttonupload.setImageURI(data)
            }else{
                Toast.makeText(context, "Seleccione imagen ",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

   companion object {
        fun newInstance() = upload_Fragment()
    }

}