package com.example.enterpriseclient


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.enterpriseclient.R.id
import com.example.enterpriseclient.R.layout
import com.example.enterpriseclient.bottomNavigationView.home.HomeFragment
import com.example.enterpriseclient.bottomNavigationView.settings.SettingsFragment
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.bottomNavigationView.user.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)

        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(toolbar)
        setBottomNavigationView()

    }


    private fun setBottomNavigationView() {
        bottomNavigationView = findViewById(id.bottom_navigation_view)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                id.navigation_search -> {
                    val fragment = HomeFragment.newInstance()

                    toolbar.title = resources.getString(R.string.toolbar_main_product)
                    openFragment(fragment)
                    true
                }

                id.navigation_settings -> {
                    val fragment = SettingsFragment.newInstance()
                    toolbar.title = resources.getString(R.string.toolbar_main_preference)
                    openFragment(fragment)
                    true
                }
                id.navigation_user -> {
                    val fragment = UserFragment.newInstance()
                    toolbar.title = resources.getString(R.string.toolbar_main_user)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cart_menu ->{
                val intent = Intent(this,CartListActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
