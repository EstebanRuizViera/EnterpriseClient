package com.example.enterpriseclient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.requestServer.RequestProduct
import kotlinx.android.synthetic.main.activity_reservation.*


class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)


        var bundle: Bundle? = intent.extras
        val message = bundle!!.getInt("id")
        Log.println(Log.INFO, null, "LLEGO AQUI" + message )

        if(message != null) {
            RequestProduct.selectProduct(this, null, productReservationName, productReservationDescription, message.toString(), thumbnailProduct)

        }

        setSupportActionBar(toolbar)


        checkAvailability.setOnClickListener(){
            val intent = Intent(this,AvailabilityActivity::class.java)
            intent.putExtra("id", message)
            startActivity(intent)
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
