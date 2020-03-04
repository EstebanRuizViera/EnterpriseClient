package com.example.enterpriseclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.enterpriseclient.bottomNavigationView.user.UserFragment
import kotlinx.android.synthetic.main.activity_drawer.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)

        val fragment = UserFragment.newInstance()
        openFragment(fragment)

    }


    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this,DrawerActivity::class.java)
        startActivity(intent)
        return true
    }

}
