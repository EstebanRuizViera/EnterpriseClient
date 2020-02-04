package com.example.enterpriseclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.enterpriseclient.R.*
import com.example.enterpriseclient.tabMenu.myFlight.MyFlightFragment
import com.example.enterpriseclient.tabMenu.search.SearchFragment
import com.example.flight.tabMenu.user.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val bottom_navigation_view: BottomNavigationView = findViewById(id.bottom_navigation_view)

        bottom_navigation_view.setOnNavigationItemSelectedListener  { menuItem ->
            when (menuItem.itemId) {
                id.navigation_search -> {
                    val fragment = SearchFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                id.navigation_reservation -> {
                    val fragment = MyFlightFragment.newInstance()
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
        bottom_navigation_view.selectedItemId = id.navigation_search
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
