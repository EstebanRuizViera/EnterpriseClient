package com.example.enterpriseclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enterpriseclient.requestServer.RequestProduct
import kotlinx.android.synthetic.main.activity_reservation.*


class ReservationActivity : AppCompatActivity() {

    private var idProduct:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        idProduct = bundle!!.getInt("id")

        getProduct()

        checkAvailability.setOnClickListener(){
            val intent = Intent(this,AvailabilityActivity::class.java)
            intent.putExtra("id", idProduct)
            startActivity(intent)
        }

    }

    private fun getProduct(){

        if(idProduct != 0) {
            RequestProduct.selectProduct(this,  productReservationName, productReservationDescription, idProduct.toString())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
