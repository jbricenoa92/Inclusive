package com.example.inclusive.view.fragments


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.FileProvider
import com.example.inclusive.R
import com.example.inclusive.viewmodel.auth.UploadViewModel
import java.io.File

class upload_Fragment : Fragment() {

        private lateinit var uploadViewModel: UploadViewModel
        private lateinit var buttonupload:ImageButton
        private var OPERATION_CAPTURE_PHOTO=1
        private var mUri: Uri? = null

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

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

        buttonupload.setOnClickListener(View.OnClickListener {
            capturePhoto()
        })


    }

    private fun capturePhoto(){
        val capturedImage = File( "My_Captured_Photo.jpg")
        if(capturedImage.exists()) {
            capturedImage.delete()
        }
        capturedImage.createNewFile()
        mUri = if(Build.VERSION.SDK_INT >= 24){
            FileProvider.getUriForFile(this.requireContext(), "info.camposha.kimagepicker.fileprovider",
                capturedImage)
        } else {
            Uri.fromFile(capturedImage)
        }

        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri)
        startActivityForResult(intent, OPERATION_CAPTURE_PHOTO)
    }

   companion object {
        fun newInstance() = upload_Fragment()
    }

}