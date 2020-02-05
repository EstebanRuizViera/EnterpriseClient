package com.example.enterpriseclient

<<<<<<< HEAD
=======
import android.content.Intent
>>>>>>> NavigationView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.enterpriseclient.R.*
<<<<<<< HEAD
import com.example.enterpriseclient.tabMenu.myFlight.MyFlightFragment
import com.example.enterpriseclient.tabMenu.search.SearchFragment
import com.example.enterpriseclient.tabMenu.user.UserFragment
=======
import com.example.enterpriseclient.bottomNavigationView.search.SearchFragment
import com.example.enterpriseclient.bottomNavigationView.settings.SettingsFragment
import com.example.enterpriseclient.bottomNavigationView.user.UserFragment
>>>>>>> NavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

<<<<<<< HEAD
        val bottom_navigation_view: BottomNavigationView = findViewById(id.bottom_navigation_view)

        bottom_navigation_view.setOnNavigationItemSelectedListener  { menuItem ->
=======
       // val intent = Intent(baseContext, LoginActivity::class.java)
       // startActivity(intent)


        val bottomNavigationView: BottomNavigationView = findViewById(id.bottom_navigation_view)

        bottomNavigationView.setOnNavigationItemSelectedListener  { menuItem ->
>>>>>>> NavigationView
            when (menuItem.itemId) {
                id.navigation_search -> {
                    val fragment = SearchFragment.newInstance()
                    openFragment(fragment)
                    true
                }
<<<<<<< HEAD
                id.navigation_reservation -> {
                    val fragment = MyFlightFragment.newInstance()
=======
                id.navigation_settings -> {
                    val fragment = SettingsFragment.newInstance()
>>>>>>> NavigationView
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
<<<<<<< HEAD
        bottom_navigation_view.selectedItemId = id.navigation_search
=======
        bottomNavigationView.selectedItemId = id.navigation_search
>>>>>>> NavigationView
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
