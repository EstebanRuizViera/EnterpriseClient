package com.example.padwordbooking

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.padwordbooking.adapter.AvailabilityAdapter
import com.example.padwordbooking.fragment.settings.SharePreferenceDarkMode
import com.example.padwordbooking.model.Availability
import com.example.padwordbooking.model.Product
import com.example.padwordbooking.myDataBase.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.activity_availability.*
import sun.bob.mcalendarview.MCalendarView
import sun.bob.mcalendarview.listeners.OnDateClickListener
import sun.bob.mcalendarview.vo.DateData
import java.text.SimpleDateFormat
import java.util.*


class AvailabilityActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var calendarView: MCalendarView
    private lateinit var recyclerView: RecyclerView
    private var idProduct: Int = 0
    private lateinit var availabilityList: ArrayList<Availability>

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availability)
        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        idProduct = bundle!!.getInt("id")

        product = bundle.getSerializable("product")!! as Product

        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerViewAvailability) as RecyclerView
        calendarView = findViewById(R.id.calendar)

        //bindProduct()
        getAvailabilityForProduct()

        getDateMarkForCustomer()

    }


    @SuppressLint("NewApi")
    private fun getAvailabilityForProduct() {
        availabilityList = product.availabilities

        var listTime = arrayListOf<Availability>()

        val layoutManagerAvailability = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerAvailability)

        for (availability in availabilityList) {

            var date = DateData(
                Integer.parseInt(availability.dateAvailability.split("-").get(0)),
                Integer.parseInt(availability.dateAvailability.split("-").get(1)),
                Integer.parseInt(availability.dateAvailability.split("-").get(2))
            )
            calendarView.markDate(date.year,date.month,date.day)

            val currentTime = Calendar.getInstance().getTime()
            var sdf = SimpleDateFormat("yyyy-MM-dd")

            var selectDate:String

            if(date.month<10){
                selectDate = ""+date.year+"-0"+date.month+"-"+date.day
            }else{
                selectDate = ""+date.year+"-"+date.month+"-"+date.day
            }
            Log.println(Log.INFO,"INFORMACIÓN",""+selectDate+" "+sdf.format(currentTime))

            if(selectDate == sdf.format(currentTime)) {
                listTime.add(availability)
            }
        }

        if(listTime.size!=0){
            val availabilityAdapter =
                AvailabilityAdapter(
                    this@AvailabilityActivity,
                    listTime,
                    product
                )
            recyclerView.adapter = availabilityAdapter
        }


    }


    fun getDateMarkForCustomer() {

        calendarView.setOnDateClickListener(object : OnDateClickListener() {
            @SuppressLint("NewApi")
            override fun onDateClick(view: View, date: DateData) {

                var listTime = arrayListOf<Availability>()

                for (availability in availabilityList) {
                    var selectDate:String
                    if(date.month<10){
                        selectDate = ""+date.year+"-0"+date.month+"-"+date.day
                    }else{
                        selectDate = ""+date.year+"-"+date.month+"-"+date.day
                    }

                    Log.println(Log.INFO,"INFORMACIÓN",""+selectDate+" "+availability.dateAvailability)
                    if( selectDate == availability.dateAvailability) {
                        listTime.add(availability)
                    }
                }
                if(listTime.size!=0){
                    val availabilityAdapter =
                        AvailabilityAdapter(
                            this@AvailabilityActivity,
                            listTime,
                            product
                        )
                    recyclerView.adapter = availabilityAdapter
                }

            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
