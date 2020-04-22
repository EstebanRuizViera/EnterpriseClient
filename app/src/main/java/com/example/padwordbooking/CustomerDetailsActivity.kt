package com.example.padwordbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.padwordbooking.cart.ShoppingCart
import com.example.padwordbooking.model.Customer
import kotlinx.android.synthetic.main.activity_customer_details.*

class CustomerDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)

        setSupportActionBar(toolbar)


        continue_button.setOnClickListener() {
            var check = 4
            if(customer_name.text.toString().equals("")){
                sm_validate_name.visibility=View.VISIBLE
            }else{
                sm_validate_name.visibility= View.INVISIBLE
                check--
            }
            if(customer_surname.text.toString().equals("")){
                sm_validate_surname.visibility=View.VISIBLE
            }else{
                sm_validate_surname.visibility=View.INVISIBLE
                check--
            }
            if(customer_email.text.toString().equals("")){
                sm_validate_email.visibility=View.VISIBLE
            }else{
                sm_validate_email.visibility=View.INVISIBLE
                check --
            }
            if(customer_phone.text.toString().equals("")){
                sm_validate_phone.visibility=View.VISIBLE
            }else{
                sm_validate_phone.visibility=View.INVISIBLE
                check --
            }
            if(check==0) {
                ShoppingCart.saveCustomer(
                    Customer(
                        customer_name.text.toString(),
                        customer_surname.text.toString(),
                        customer_email.text.toString(),
                        customer_phone.text.toString()
                    )
                )
                val intent = Intent(this, SummaryActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
