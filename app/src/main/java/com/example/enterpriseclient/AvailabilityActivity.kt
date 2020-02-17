package com.example.enterpriseclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.model.Availability
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestProduct
import com.example.enterpriseclient.requestServer.RequestUser
import kotlinx.android.synthetic.main.activity_availability.*
import kotlinx.android.synthetic.main.activity_availability.toolbar

class AvailabilityActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availability)

        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        val message = bundle!!.getInt("id")

        val calendarView = findViewById<CalendarView>(R.id.calendar)

        var number = usersViewModel.getUserId(1)

        var availabilityList = arrayListOf<Availability>()

        var selectedDate:String

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show()
        }

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAvailability)

        val layoutManagerAvailability = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerAvailability)

        if(message != null) {
            RequestProduct.selectAvailabilityForProduct(this, calendar, availabilityList,recyclerView, message.toString())
        }


        addToCart.setOnClickListener{
            if(!number.equals("")){
                RequestUser.createReservation(this, 1524, "2020/10/10", number, message.toString())
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
