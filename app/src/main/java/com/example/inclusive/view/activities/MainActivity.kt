    package com.example.inclusive.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.inclusive.R
import com.example.inclusive.view.fragments.HomeFragment
import com.example.inclusive.view.fragments.upload_Fragment
import kotlinx.android.synthetic.main.activity_main.*

    class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

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

                        Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }

            }
       }

        private fun loadFragment(fragment: Fragment) {
            // load fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

