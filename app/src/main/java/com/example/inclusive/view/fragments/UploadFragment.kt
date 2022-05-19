package com.example.inclusive.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.inclusive.R
import com.example.inclusive.viewmodel.auth.UploadViewModel

class upload_Fragment : Fragment() {

        private lateinit var uploadViewModel: UploadViewModel


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

      //  val getImage=view.findViewById<Button>(R.id.imageViewUpload)


    /*    getImage.setOnClickListener(View.OnClickListener {
            uploadViewModel.getimage(this.requireActivity(),savedInstanceState)
        })
*/
    }

   companion object {
        fun newInstance() = upload_Fragment()
    }

}