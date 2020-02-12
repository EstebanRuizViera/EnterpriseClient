package com.example.enterpriseclient


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.enterpriseclient.R.*
import com.example.enterpriseclient.bottomNavigationView.search.SearchFragment
import com.example.enterpriseclient.bottomNavigationView.settings.SettingsFragment
import com.example.enterpriseclient.bottomNavigationView.user.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(id.bottom_navigation_view)

        bottomNavigationView.setOnNavigationItemSelectedListener  { menuItem ->

            when (menuItem.itemId) {
                id.navigation_search -> {
                    val fragment = SearchFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                id.navigation_settings -> {
                    val fragment = SettingsFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                id.navigation_user -> {
                    val fragment = UserFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.selectedItemId = id.navigation_search

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
