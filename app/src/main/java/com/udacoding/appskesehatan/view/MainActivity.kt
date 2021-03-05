package com.udacoding.appskesehatan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.udacoding.appskesehatan.R
import com.udacoding.appskesehatan.databinding.ActivityMainBinding
import com.udacoding.appskesehatan.view.home.HistoryFragment
import com.udacoding.appskesehatan.view.home.HomeFragment
import com.udacoding.appskesehatan.view.home.InsertFragment
import com.udacoding.appskesehatan.view.home.ProfileFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = HomeFragment()
        addFragment(fragment, "Home")

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_home -> {
                val fragment = HomeFragment()
                addFragment(fragment, "Home")
                true
            }

            R.id.menu_insert -> {
                val fragment = InsertFragment()
                addFragment(fragment, "Insert")
                true
            }

            R.id.menu_history -> {
                val fragment = HistoryFragment()
                addFragment(fragment, "History")
                true
            }

            R.id.menu_profile -> {
                val fragment = ProfileFragment()
                addFragment(fragment, "Profile")
                true
            }

            else -> false
        }
    }

    private fun addFragment(fragment: Fragment, judul: String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_home_container, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()

        binding.topAppBar.title = judul
    }
}