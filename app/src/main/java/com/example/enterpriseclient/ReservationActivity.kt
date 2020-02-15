package com.example.enterpriseclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_reservation.*

class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        setSupportActionBar(toolbar)

        checkDisponibility.setOnClickListener(){
            val intent = Intent(this,AvailabilityActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
