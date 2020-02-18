package com.example.enterpriseclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.model.AvailabilityPojo
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestAvailability
import com.example.enterpriseclient.requestServer.RequestReservation
import kotlinx.android.synthetic.main.activity_availability.*

class AvailabilityActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var calendarView:CalendarView
    private lateinit var recyclerView:RecyclerView
    private var idProduct:Int = 0
    private var selectedDate:String = "2020-10-10"
    private var idUser:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availability)
        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        idProduct = bundle!!.getInt("id")

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        addProductToCard()
        getAvailabilityForProduct()

    }

    private fun getAvailabilityForProduct(){
        var availabilityList = arrayListOf<AvailabilityPojo>()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAvailability)

        val layoutManagerAvailability = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerAvailability)

        if(idProduct != null) {
            RequestAvailability.selectAvailabilityForProduct(this, availabilityList,recyclerView, idProduct.toString())
        }
    }

    private fun addProductToCard(){
        calendarView = findViewById(R.id.calendar)
        idUser = usersViewModel.getUserId(1)

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate = ""+year + "-" + (month + 1) + "-" + dayOfMonth
            Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show()
        }

        addToCart.setOnClickListener{
            if(!idProduct.equals("")){
                RequestReservation.createReservation(this, 1524, selectedDate, idUser, idProduct.toString())
            } else {
                Toast.makeText(this, "You have to sign in to book a product ", Toast.LENGTH_LONG).show()
            }

        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
