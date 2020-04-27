package com.example.padwordbooking

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.padwordbooking.adapter.CartAdapter
import com.example.padwordbooking.cart.ShoppingCart
import com.example.padwordbooking.fragment.settings.SharePreferenceDarkMode
import com.example.padwordbooking.model.Customer
import com.example.padwordbooking.model.Product
import com.example.padwordbooking.myDataBase.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.activity_cart_list.*
import kotlinx.android.synthetic.main.activity_cart_list.toolbar
import kotlinx.android.synthetic.main.activity_customer_details.*


class CartListActivity : AppCompatActivity() {

    private lateinit var userViewModel:UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)
        setSupportActionBar(toolbar)

        userViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        checkout.setOnClickListener{
            val intent = Intent(this, CustomerDetailsActivity::class.java)
            intent.putExtra("guest",true)
            startActivity(intent)
        }

        checkoutLogin.setOnClickListener{
            val builder = AlertDialog.Builder(this)

            setInfoClientAlert(builder)

            builder.setTitle("Log in")
            builder.setPositiveButton("Continue") { dialog: DialogInterface?, which: Int ->
                if(userViewModel.getUserId(1) != "0"){
                    ShoppingCart.saveCustomer(
                        Customer(
                            userViewModel.getUser(1)!!.get(0).id_remoto,
                            userViewModel.getUser(1)!!.get(0).name,
                            userViewModel.getUser(1)!!.get(0).name,
                            userViewModel.getUser(1)!!.get(0).email,
                            userViewModel.getUser(1)!!.get(0).email
                        )
                    )
                    val intent = Intent(this, SummaryActivity::class.java)
                    intent.putExtra("guest",false)
                    startActivity(intent)
                }
            }
            builder.setNegativeButton("Cancel") { dialog: DialogInterface?, which: Int ->

            }
            builder.show()
        }

        setRecyclerView()
    }

    private fun setRecyclerView(){

        var productsList =
            ShoppingCart.getProducts()

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
        number_item.text = ""+ resources.getString(R.string.carlist_number_item)+" "+productsList.size.toString()
    }

    fun calculatePrice(productsList:MutableList<Product>){
        var totalPrice = 0.0
        for(productItem in productsList){
            totalPrice += productItem.availabilities[0].price
        }

        total_price.text = ""+ totalPrice+"€"
    }


    fun setInfoClientAlert(builder:AlertDialog.Builder){
        val alertContent = layoutInflater.inflate(R.layout.alert_dialog, null)

        val alertName = alertContent.findViewById<TextView>(R.id.alert_name)
        val alertEmail = alertContent.findViewById<TextView>(R.id.alert_email)

        val buttonAlert = alertContent.findViewById<LinearLayout>(R.id.alert_dialog_linear)



        if(userViewModel.getUserId(1) != "0"){
            alertName.text = userViewModel.getUser(1)!!.get(0).name
            alertEmail.text = userViewModel.getUser(1)!!.get(0).email
        }else{
            buttonAlert.setOnClickListener{
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }

        builder.setView(alertContent)
    }


    override fun onSupportNavigateUp(): Boolean {
        var intent = Intent(this, DrawerActivity::class.java)
        startActivity(intent)
        return true
    }
}
