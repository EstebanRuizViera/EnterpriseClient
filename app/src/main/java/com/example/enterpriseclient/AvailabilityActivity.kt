package com.example.enterpriseclient

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.adapter.AvailabilityAdapter
import com.example.enterpriseclient.model.CartItem
import com.example.enterpriseclient.model.Product
import com.example.enterpriseclient.cart.ShoppingCart
import com.example.enterpriseclient.fragment.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.model.Availability
import com.example.enterpriseclient.model.Distribution
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestAvailability
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

    private lateinit var productArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availability)
        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        idProduct = bundle!!.getInt("id")

        productArray = bundle!!.getStringArray("product")!!

        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerViewAvailability) as RecyclerView
        calendarView = findViewById(R.id.calendar)

        bindProduct()
        getAvailabilityForProduct()

        getDateMarkForCustomer()

    }


    private fun getAvailabilityForProduct() {
        availabilityList = arrayListOf<Availability>()

        val layoutManagerAvailability = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerAvailability)

        if (idProduct != null) {
            RequestAvailability.selectAvailabilityForProduct(
                this,
                availabilityList,
                recyclerView,
                idProduct.toString(),
                calendarView
            )
        }
    }


    @SuppressLint("CheckResult")
    fun bindProduct() {

        var product: Product =
            Product(
                Integer.parseInt(productArray[0]),
                productArray[1],
                productArray[2],
                productArray[3],
                productArray[4],
                Distribution(0,"",123,123,12,10)
            )

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

                var newAvailabilityList = arrayListOf<Availability>()

                for (availability in availabilityList) {

                    if (LocalDate.of(date.year, date.month, date.day) == LocalDate.of(
                            Integer.parseInt(availability.dateAvailability.split("-").get(0)),
                            Integer.parseInt(availability.dateAvailability.split("-").get(1)),
                            Integer.parseInt(availability.dateAvailability.split("-").get(2))
                        )
                    ) {
                        newAvailabilityList.add(availability)
                    }
                }

                //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
                val availabilityAdapter =
                    AvailabilityAdapter(
                        this@AvailabilityActivity,
                        newAvailabilityList
                    )
                recyclerView.adapter = availabilityAdapter


            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
