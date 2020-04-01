package com.example.enterpriseclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.enterpriseclient.fragment.user.LoginFragment
import kotlinx.android.synthetic.main.activity_customer_details.*
import kotlinx.android.synthetic.main.activity_customer_details.toolbar

class CustomerDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)

        setSupportActionBar(toolbar)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
