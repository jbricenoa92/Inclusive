    package com.example.inclusive.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import com.example.inclusive.R
import com.example.inclusive.view.fragments.HomeFragment
import com.example.inclusive.view.fragments.upload_Fragment
import com.example.inclusive.viewmodel.auth.AuthViewModel

import kotlinx.android.synthetic.main.activity_main.*


    class MainActivity : AppCompatActivity() {

        private val viewModel:AuthViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            viewModel.loggerStatus.observe(this, Observer { log ->
                intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
            })
            loadFragment(HomeFragment.newInstance())
            buttonnavigationView.setOnItemSelectedListener { view ->
                var fragment: Fragment
                when (view.itemId) {
                    R.id.navigation_upload -> {
                      fragment = upload_Fragment()
                        loadFragment(fragment)
                        true
                    }
                    R.id.navigation_translate -> {
                        fragment = HomeFragment()
                        loadFragment(fragment)
                        true
                    }
                    R.id.navigation_logout -> {
                        viewModel.logout()
                        true
                    }
                    else -> false
                }
            }
       }

        private fun loadFragment(fragment: Fragment) {
            // load fragment
            supportFragmentManager.beginTransaction()
                .remove(upload_Fragment())
                .replace(R.id.optionstranslate, fragment)
                .commit()
        }

    }

