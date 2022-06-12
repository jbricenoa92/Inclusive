package com.example.inclusive.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import com.example.inclusive.R
enum class ProviderType{
    BASIC
}

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

       onBackPressedDispatcher.addCallback(this) {
           this.isEnabled=false
       }

    }
}