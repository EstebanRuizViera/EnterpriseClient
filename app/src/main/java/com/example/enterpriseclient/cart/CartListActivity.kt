package com.example.enterpriseclient.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enterpriseclient.CustomerDetailsActivity
import com.example.enterpriseclient.R
import com.example.enterpriseclient.adapter.CartAdapter
import com.example.enterpriseclient.fragment.settings.SharePreferenceDarkMode
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

        var productsList = ShoppingCart.getCart()

        val layoutManagerProducts = GridLayoutManager(this, 1)
        recyclerViewCart.setLayoutManager(layoutManagerProducts)

//        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
        val productAdapter =
            CartAdapter(
                this,
                productsList
            )
        recyclerViewCart.setAdapter(productAdapter)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
