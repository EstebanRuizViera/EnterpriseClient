package com.example.enterpriseclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel
import kotlinx.android.synthetic.main.activity_reservation.*


class ReservationActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    private var idProduct:Int = 0
    private var option = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
        .error(R.drawable.loading_shape)

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        idProduct = bundle!!.getInt("id")

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        getProduct()

        checkAvailability.setOnClickListener(){
            val intent = Intent(this,AvailabilityActivity::class.java)
            intent.putExtra("id", idProduct)
            startActivity(intent)
        }

    }

    private fun getProduct(){

        if(idProduct != 0) {

            var listProduct = productViewModel.getProduct(idProduct)

            if(listProduct != null){
                var product = listProduct[0]

                productReservationName.text = product.name
                productReservationDescription.text =product.description
                Glide.with(this).load(product.image_url).apply(option)
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
