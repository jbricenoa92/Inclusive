    package com.example.inclusive.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.inclusive.R
import com.example.inclusive.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

    enum class ProviderType{
        BASIC
    }

    class MainActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding

        private lateinit var storage:StorageReference


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spLista: Spinner= findViewById(R.id.spinner_1)
        val spLista2: Spinner= findViewById(R.id.spinner_2)
        val imageView=findViewById<ImageView>(R.id.showgif)
        val edittext:EditText=findViewById(R.id.editText)
               storage=FirebaseStorage.getInstance().getReference()
               val ONE_MEGABYTE: Long = 1024 * 1024
                storage.getBytes(ONE_MEGABYTE).addOnSuccessListener {
                    // Data for "images/island.jpg" is returned, use this as needed
                    val pathReference = storage.child("Hello.gif")
                    Glide.with(this).load(pathReference).into(imageView)

                }.addOnFailureListener {
                    // Handle any errors
                 Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()

                }


            val adp=ArrayAdapter.createFromResource(this,R.array.Lista,android.R.layout.simple_spinner_item)
            .also{adapter->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
                spLista.adapter = adapter
                spLista2.adapter = adapter
        }


    }
}


