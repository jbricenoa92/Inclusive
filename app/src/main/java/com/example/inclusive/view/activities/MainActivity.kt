    package com.example.inclusive.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

import com.example.inclusive.R
import com.example.inclusive.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

    enum class ProviderType{
        BASIC
    }
@AndroidEntryPoint
    class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

    }

}


