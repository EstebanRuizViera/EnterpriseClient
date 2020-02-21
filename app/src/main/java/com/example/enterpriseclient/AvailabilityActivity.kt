package com.example.enterpriseclient

import android.os.Bundle
import android.view.Gravity
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.model.AvailabilityPojo
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestAvailability
import com.example.enterpriseclient.requestServer.RequestReservation
import kotlinx.android.synthetic.main.activity_availability.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AvailabilityActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var calendarView:CalendarView
    private lateinit var recyclerView:RecyclerView
    private var idProduct:Int = 0
    private var selectedDate:String = ""
    private var idUser:String = ""
    private lateinit var availabilityList:ArrayList<AvailabilityPojo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availability)
        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        idProduct = bundle!!.getInt("id")

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAvailability)
        calendarView = findViewById(R.id.calendar)

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        selectedDate = sdf.format(Date(calendarView.date))

        addProductToCard()
        getAvailabilityForProduct()

    }

    private fun getAvailabilityForProduct(){
        availabilityList = arrayListOf<AvailabilityPojo>()

        val layoutManagerAvailability = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerAvailability)

        if(idProduct != null) {
            RequestAvailability.selectAvailabilityForProduct(this, availabilityList,recyclerView, idProduct.toString())
        }
    }

    private fun addProductToCard(){

        idUser = usersViewModel.getUserId(1)

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate = ""+year + "-" + (month + 1) + "-" + dayOfMonth
        }

        addToCart.setOnClickListener{
            if(!idProduct.equals("")){
                RequestReservation.createReservation(this, 1524, selectedDate, idUser, idProduct.toString())
            } else {
                var toast=Toast.makeText(this, "You have to sign in to book a product ", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
                toast.show()
            }

        }
    }

    fun selectTimeAvailability(){

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
