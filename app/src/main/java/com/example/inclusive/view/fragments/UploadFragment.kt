package com.example.inclusive.view.fragments



import android.app.Activity
import android.content.Intent
import android.net.Uri

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

import com.example.inclusive.R
import com.example.inclusive.viewmodel.auth.UploadViewModel


class upload_Fragment : Fragment() {

        private lateinit var uploadViewModel: UploadViewModel
        private lateinit var buttonupload:ImageButton
        private val REQUEST_IMAGE_CAPTURE = 1
        private lateinit var locationForPhotos: Uri

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
        var uri:Uri
        buttonupload.setOnClickListener(View.OnClickListener {
            getphoto()
      })
    }

    fun getphoto(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        starForActivityGalerry.launch(intent)

    }

    private val starForActivityGalerry = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode==Activity.RESULT_OK){
            val data =it.data?.data
            buttonupload.setImageURI(data)

        }
    }

   companion object {
        fun newInstance() = upload_Fragment()
    }

}