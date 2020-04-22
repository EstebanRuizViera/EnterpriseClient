package com.example.padwordbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.padwordbooking.adapter.ProductListAdapter
import com.example.padwordbooking.adapter.SummaryAdapter
import com.example.padwordbooking.cart.ShoppingCart
import com.example.padwordbooking.model.Product
import com.example.padwordbooking.requestServer.RequestReservation
import kotlinx.android.synthetic.main.activity_product.toolbar
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : AppCompatActivity() {

    private var totalPrice = 0.0
    private var checkGuest = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        setSupportActionBar(toolbar)

        setInfoDetails()
        setProductList()

        checkGuest= intent.getBooleanExtra("guest",true)

        placeOrder.setOnClickListener {
            createReservation()
        }
    }

    fun setInfoDetails(){
        var customer = ShoppingCart.getCustomer()
        summary_name.text = getString(R.string.summary_name)+" "+customer.name+" "+customer.surname
        summary_email.text = getString(R.string.summary_email)+" "+customer.email
        summary_phone.text = getString(R.string.summary_phone)+" "+customer.telephone
    }

    fun setProductList(){
        var products = ShoppingCart.getProducts()

        val productAdapter =
            SummaryAdapter(
                this,
                products
            )

        val layoutManagerProducts = GridLayoutManager(this, 1)
        recyclerViewSummary.layoutManager = layoutManagerProducts
        recyclerViewSummary.adapter = productAdapter

        for(productItem in products){
            totalPrice += productItem.availabilities[0].price
        }
        summary_products.text = ""+ totalPrice+"€"
        summary_total.text = ""+ totalPrice+"€"
    }

    fun createReservation(){
        RequestReservation.createReservation(this,totalPrice,checkGuest)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
