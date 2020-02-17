package com.example.enterpriseclient


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.enterpriseclient.R.*
import com.example.enterpriseclient.bottomNavigationView.home.HomeFragment
import com.example.enterpriseclient.bottomNavigationView.settings.SettingsFragment
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.bottomNavigationView.user.UserFragment
import com.example.enterpriseclient.myDataBase.model.User
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_reservation.*

class MainActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(toolbar);

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        if(usersViewModel.getUserIdLocal(1)==0){
            usersViewModel.saveUser(User(1,"","You are not logged in","Login","Register"))
            Log.println(Log.INFO,null,"Guardado ")
        }else{
            Log.println(Log.INFO,null,"No guardado ")
        }

        val bottomNavigationView: BottomNavigationView = findViewById(id.bottom_navigation_view)

        bottomNavigationView.setOnNavigationItemSelectedListener  { menuItem ->

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

    public fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
//
//    override fun onPrepareOptionsMenu(menu :Menu):Boolean {
//        //Se accede al ítem usando el id que
//        //tiene dentro del menú directamente
//        var opcion1 = menu.findItem(R.id.cart_menu)
//        opcion1.setEnabled(true)
//
//        return true
//    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_action_bar, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        if (id == R.id.cart_menu) {
//            val b = Intent(this, AvailabilityActivity::class.java)
//            startActivity(b)
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
