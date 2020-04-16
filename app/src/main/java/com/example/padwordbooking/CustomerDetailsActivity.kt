package com.example.padwordbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_customer_details.*

class CustomerDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)

        setSupportActionBar(toolbar)

        var check :Boolean
        continueButton.setOnClickListener() {

            if(CustomerName.text.toString().equals("")){
                sm_validate_name.visibility=View.VISIBLE
                check = false
            }else{
                sm_validate_name.visibility= View.INVISIBLE
                check = true
            }
            if(CustomerSurname.text.toString().equals("")){
                sm_validate_surname.visibility=View.VISIBLE
                check = false
            }else{
                sm_validate_surname.visibility=View.INVISIBLE
                check = true
            }
            if(CustomerEmail.text.toString().equals("")){
                sm_validate_email.visibility=View.VISIBLE
                check = false
            }else{
                sm_validate_email.visibility=View.INVISIBLE
                check = true
            }
            if(CustomerPhone.text.toString().equals("")){
                sm_validate_phone.visibility=View.VISIBLE
                check = false
            }else{
                sm_validate_phone.visibility=View.INVISIBLE
                check = true
            }
            if(check) {
                val intent = Intent(this, SummaryActivity::class.java)
                 startActivity(intent)
            }
        }


//        continueButton.setOnClickListener(){
//            val intent = Intent(this, SummaryActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
