package com.udacoding.appskesehatan.view.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.udacoding.appskesehatan.R
import com.udacoding.appskesehatan.databinding.ActivityAuthBinding
import com.udacoding.appskesehatan.view.auth.fragment.LoginFragment

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = LoginFragment()
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_auth_container, fragment)
            .commit()
    }
}