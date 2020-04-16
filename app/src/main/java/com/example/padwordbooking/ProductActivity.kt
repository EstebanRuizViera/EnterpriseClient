package com.example.padwordbooking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.padwordbooking.fragment.settings.SharePreferenceDarkMode
import com.example.padwordbooking.model.Product
import com.ms.square.android.expandabletextview.ExpandableTextView
import kotlinx.android.synthetic.main.activity_product.*
import java.lang.IndexOutOfBoundsException
import kotlin.math.roundToInt


class ProductActivity : AppCompatActivity() {

    private var idProduct:Int = 0
    private var option = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
        .error(R.drawable.loading_shape)

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        idProduct = bundle!!.getInt("id")

        product = bundle.getSerializable("product")!! as Product

        getProduct()


        checkAvailability.setOnClickListener(){
            val intent = Intent(this, AvailabilityActivity::class.java)
            intent.putExtra("id", idProduct)
            intent.putExtra("product", bundle.getSerializable("product"))
            startActivity(intent)
        }

    }

    private fun getProduct(){

            try{
                productReservationName.text = product.name
                expand_text_view.text =product.description
                reservationPrice.text = ""+product.availabilities[0].price.roundToInt()+"€"
                Glide.with(this).load(product.img).apply(option)
                    .into(thumbnailProduct)
            }catch (ex:IndexOutOfBoundsException){
                productReservationName.text = product.name
                expand_text_view.text =product.description
                reservationPrice.text = ""+150+"€"
                Glide.with(this).load(product.img).apply(option)
                    .into(thumbnailProduct)
            }
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
