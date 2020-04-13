package com.example.padwordbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.padwordbooking.adapter.CartAdapter
import com.example.padwordbooking.cart.ShoppingCart
import com.example.padwordbooking.fragment.settings.SharePreferenceDarkMode
import com.example.padwordbooking.model.CartItem
import kotlinx.android.synthetic.main.activity_cart_list.*

class CartListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)
        setSupportActionBar(toolbar)

        checkout.setOnClickListener{
            val intent = Intent(this, CustomerDetailsActivity::class.java)
            startActivity(intent)
        }

        setRecyclerView()
    }

    private fun setRecyclerView(){

        var productsList =
            ShoppingCart.getCart()

        val layoutManagerProducts = GridLayoutManager(this, 1)
        recyclerViewCart.setLayoutManager(layoutManagerProducts)

//        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
        val cartAdapter =
            CartAdapter(
                this,
                productsList
            )
        recyclerViewCart.setAdapter(cartAdapter)
        calculatePrice(productsList)

    }

    fun calculatePrice(productsList:MutableList<CartItem>){
        var totalPrice = 0.0
        for(cartItem in productsList){
            totalPrice += cartItem.product.availabilities[0].price
        }

        total_price.text = ""+ totalPrice+"€"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
