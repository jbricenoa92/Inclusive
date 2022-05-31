    package com.example.inclusive.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.inclusive.R
import com.example.inclusive.view.activities.MainActivity.KeyboardktUtils.hideKeyboard
import com.example.inclusive.view.activities.MainActivity.KeyboardktUtils.toggleSoftInput
import com.example.inclusive.view.fragments.*
import com.example.inclusive.viewmodel.auth.AuthViewModel
import com.example.inclusive.viewmodel.auth.HomeViewModel

import kotlinx.android.synthetic.main.activity_main.*


    class MainActivity : AppCompatActivity() {

        private val viewModel:AuthViewModel by viewModels()
        private val homeviewModel: HomeViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            viewModel.loggerStatus.observe(this, Observer { log ->
                intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
            })

            var teclado=homeviewModel.selectedItem.observe(this) {
                var fragment: Fragment
                // Log.e("itimprimir",it.toString())
                when (it) {
                    0 -> {
                        toggleSoftInput(activity_main.findViewById(R.id.showteclado))
                    }
                    1 -> {
                        hideKeyboard(activity_main.findViewById(R.id.showteclado))
                        fragment = tecladoBrailleFragment()
                        loadFragmentnavOptionTranstale(fragment)
                    }
                    2 -> {
                        hideKeyboard(activity_main.findViewById(R.id.showteclado))
                        fragment = tDagtiloFragment()
                        loadFragmentnavOptionTranstale(fragment)
                    }
                }
            }
            loadFragmentnavBar(HomeFragment.newInstance())
            navigationBar()

       }



        private fun navigationBar() {
            buttonnavigationView.setOnItemSelectedListener { view ->
                var fragment: Fragment
                when (view.itemId) {
                    R.id.navigation_upload -> {
                        fragment = upload_Fragment()

                        loadFragmentnavBar(fragment)

                        true
                    }
                    R.id.navigation_translate -> {
                        fragment = HomeFragment()
                        loadFragmentnavBar(fragment)
                        KeyboardktUtils.showKeyboard(activity_main.findViewById(R.id.showteclado))
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


        object KeyboardktUtils{
            fun showKeyboard(view: View) {
                val imm = view.context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(view, 0)

            }

            fun hideKeyboard(view: View) {
                val imm = view.context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }

            fun toggleSoftInput(view: View) {
                val imm = view.context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.toggleSoftInput(0, 0)
            }
        }

        private fun loadFragmentnavBar(fragment: Fragment) {
            // load fragment
            supportFragmentManager.beginTransaction()
                .remove(upload_Fragment())
                .replace(R.id.optionstranslate, fragment)
                .commit()
        }

        private fun loadFragmentnavOptionTranstale(fragment: Fragment) {
            // load fragment
            supportFragmentManager.beginTransaction()
                .remove(upload_Fragment())
                .replace(R.id.showteclado, fragment)
                .commit()
        }
        private fun loadFragmentnavshowTeclado(fragment: Fragment) {
            // load fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.showteclado, fragment)
                .commit()
        }

        private fun removeFragmentnavshowTeclado() {
            // load fragment
            val fragment =supportFragmentManager.findFragmentByTag("uploadname")
                    fragment?.let {
                        val transaction=supportFragmentManager.beginTransaction()
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        transaction.remove(it).commitAllowingStateLoss()
                    }

        }


    }

