package com.example.enterpriseclient


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.example.enterpriseclient.R.id
import com.example.enterpriseclient.R.layout
import com.example.enterpriseclient.bottomNavigationView.home.HomeFragment
import com.example.enterpriseclient.bottomNavigationView.settings.SettingsFragment
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.bottomNavigationView.user.UserFragment
import com.example.enterpriseclient.myDataBase.model.User
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.mySynchronized.SynchronizedLocalDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_reservation.*


class MainActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var synchronizedLocalDatabase: SynchronizedLocalDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(toolbar);

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }


        //Registro que contendrá la información del usuario que este logeado
        setFirthUserLocalDatabase()

        setBottomNavigationView()

        syncronizedProduct()

    }

    private fun syncronizedProduct() {
        synchronizedLocalDatabase = SynchronizedLocalDatabase(this)
        synchronizedLocalDatabase.syncronizedProduct()
    }

    private fun setFirthUserLocalDatabase() {
        if (usersViewModel.getUserIdLocal(1) == 0) {
            usersViewModel.saveUser(User(1, "", "You are not logged in", "Login", "Register"))
            Log.println(Log.INFO, null, "Guardado ")
        } else {
            Log.println(Log.INFO, null, "No guardado ")
        }
    }

    private fun setBottomNavigationView() {
        bottomNavigationView = findViewById(id.bottom_navigation_view)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                id.navigation_search -> {
                    val fragment = HomeFragment.newInstance()
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

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
