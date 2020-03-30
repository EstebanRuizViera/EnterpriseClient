package com.example.enterpriseclient

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.cart.CartItem
import com.example.enterpriseclient.cart.Product
import com.example.enterpriseclient.cart.ShoppingCart
import com.example.enterpriseclient.fragment.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.model.AvailabilityPojo
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestAvailability
import com.example.enterpriseclient.requestServer.RequestReservation
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
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
        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availability)
        setSupportActionBar(toolbar)


        var bundle: Bundle? = intent.extras
        idProduct = bundle!!.getInt("id")


        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)


        recyclerView = findViewById(R.id.recyclerViewAvailability) as RecyclerView
        calendarView = findViewById(R.id.calendar)


//        addProductToCard()
        bindProduct()
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


    @SuppressLint("CheckResult")
    fun bindProduct() {

        var product: Product = Product(idProduct,"prueba","descripcion","10","")

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


        Toast.makeText(this, "${product.name} added to your cart", Toast.LENGTH_SHORT).show()

    }

    //    private fun addProductToCard(){
//
//        val sdf = SimpleDateFormat("yyyy-MM-dd")
//        selectedDate = sdf.format(Date(calendarView.date))
//
//        idUser = usersViewModel.getUserId(1)
//
//        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            selectedDate = ""+year + "-" + (month + 1) + "-" + dayOfMonth
//        }
//
//        addToCart.setOnClickListener{
//            if(!idProduct.equals("")){
//                RequestReservation.createReservation(this, 1524, selectedDate, idUser, idProduct.toString())
//            } else {
//                var toast=Toast.makeText(this, "This product don´t exist", Toast.LENGTH_LONG)
//                toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
//                toast.show()
//            }
//
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
