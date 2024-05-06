package com.example.chapter_4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.chapter_4.Model.SharedPreference
import com.example.chapter_4.R
import com.example.chapter_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mySharedPreference = SharedPreference(this)

        if (mySharedPreference.isLoggedIn()) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
        } else {
            findNavController(R.id.nav_host_fragment).navigate(R.id.loginFragment)
        }



    }
}