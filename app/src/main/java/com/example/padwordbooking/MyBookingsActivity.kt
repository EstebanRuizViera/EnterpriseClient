package com.example.padwordbooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.padwordbooking.model.ProductProfile
import com.example.padwordbooking.myDataBase.viewModel.UsersViewModel
import com.example.padwordbooking.requestServer.RequestProduct
import kotlinx.android.synthetic.main.activity_my_bookings.*

class MyBookingsActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bookings)

        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        setSupportActionBar(toolbar)

        getMyBookings()

    }


    fun getMyBookings() {

        var productsList = arrayListOf<ProductProfile>()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProfile)

        val layoutManagerProducts = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerProducts)

        var number = usersViewModel.getUserId(1)

        if (number.equals("")) {
            number = "1"
        }

        RequestProduct.selectAllProductsForCustomer(this,usersViewModel, productsList, recyclerView, number)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
