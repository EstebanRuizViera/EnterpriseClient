package com.example.enterpriseclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enterpriseclient.requestServer.RequestProduct
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

        RequestProduct.selectProduct(this, null, productReservationName, productReservationDescription)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
