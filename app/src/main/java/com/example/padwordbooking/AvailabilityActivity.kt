package com.example.padwordbooking

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.padwordbooking.adapter.AvailabilityAdapter
import com.example.padwordbooking.model.CartItem
import com.example.padwordbooking.model.Product
import com.example.padwordbooking.cart.ShoppingCart
import com.example.padwordbooking.fragment.settings.SharePreferenceDarkMode
import com.example.padwordbooking.model.Availability
import com.example.padwordbooking.myDataBase.viewModel.UsersViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_availability.*
import sun.bob.mcalendarview.MCalendarView
import sun.bob.mcalendarview.listeners.OnDateClickListener
import sun.bob.mcalendarview.vo.DateData
import java.time.LocalDate


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

        product = bundle!!.getSerializable("product")!! as Product

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

            if(LocalDate.of(date.year,date.month,date.day) == LocalDate.now()) {

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


    @SuppressLint("CheckResult")
    fun bindProduct() {

        Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {
            addToCart.setOnClickListener { view ->

                val item = CartItem(product)

                ShoppingCart.addItem(item)
                //notify users
                Snackbar.make(
                    view,
                    "${product.name} added to your cart",
                    Snackbar.LENGTH_LONG
                ).show()

                it.onNext(ShoppingCart.getCart())

            }
        }).subscribe { cart ->
            var quantity = 0
            cart.forEach { cartItem ->
                quantity += cartItem.quantity
            }
            Toast.makeText(this, "Cart size $quantity", Toast.LENGTH_SHORT).show()
        }

    }

    fun getDateMarkForCustomer() {

        calendarView.setOnDateClickListener(object : OnDateClickListener() {
            @SuppressLint("NewApi")
            override fun onDateClick(view: View, date: DateData) {

                var listTime = arrayListOf<Availability>()

                for (availability in availabilityList) {

                    if (LocalDate.of(date.year, date.month, date.day) == LocalDate.of(
                            Integer.parseInt(availability.dateAvailability.split("-").get(0)),
                            Integer.parseInt(availability.dateAvailability.split("-").get(1)),
                            Integer.parseInt(availability.dateAvailability.split("-").get(2))
                        )
                    ) {
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
