package com.example.enterpriseclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.enterpriseclient.fragment.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.model.Distribution
import com.example.enterpriseclient.model.Product
import kotlinx.android.synthetic.main.activity_reservation.*


class ReservationActivity : AppCompatActivity() {

    private var idProduct:Int = 0
    private var option = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
        .error(R.drawable.loading_shape)

    private lateinit var productArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        idProduct = bundle!!.getInt("id")

        productArray = bundle!!.getStringArray("product")!!

        getProduct()

        checkAvailability.setOnClickListener(){
            val intent = Intent(this,AvailabilityActivity::class.java)
            intent.putExtra("id", idProduct)
            intent.putExtra("product", bundle!!.getStringArray("product"))
            startActivity(intent)
        }

    }

    private fun getProduct(){

        if(idProduct != 0) {

            var product: Product =
                Product(
                    Integer.parseInt(productArray[0]),
                    productArray[1],
                    productArray[2],
                    productArray[3],
                    productArray[4], Distribution(0,"",123,123,12,10)

                )

            if(product != null){

                productReservationName.text = product.name
                productReservationDescription.text =product.description
                Glide.with(this).load(product.img).apply(option)
                    .into(thumbnailProduct)
            }else{
                productReservationName.text = "Product not found"
                productReservationDescription.text ="Try again later"
                Glide.with(this).load("").apply(option)
                    .into(thumbnailProduct)
            }

        }
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
