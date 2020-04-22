package com.example.padwordbooking.requestServer

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.padwordbooking.ConfirmationActivity
import com.example.padwordbooking.Constants
import com.example.padwordbooking.DrawerActivity
import com.example.padwordbooking.cart.ShoppingCart
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class RequestReservation {
    companion object {


        //------------- RESERVATION --------------------

        @JvmStatic
        fun createReservation(
            context: Context,
            total: Double,
            checkGuest:Boolean
        ) {

            val reservationJsonobj = JSONObject()
            val productsJsonArray = JSONArray()

            getDataReservation(reservationJsonobj,productsJsonArray,total,checkGuest)

            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/api/create_reservations"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, reservationJsonobj,
                Response.Listener {
                    RequestReport.generateReportListProduct(context)
                    Log.println(Log.INFO, null, "create reservation succesfull")
                    val intent = Intent(context, ConfirmationActivity::class.java)
                    context.startActivity(intent)
                },
                Response.ErrorListener {
                    var toast= Toast.makeText(context, "You have to sign in to book a product ", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
                    toast.show()
                    Log.println(Log.INFO, null, "Error creating your reservation. Try again later")
                }) {}

            queue.add(req)
        }

        fun getDataReservation(reservationJsonobj:JSONObject,productsJsonArray:JSONArray,total: Double,checkGuest:Boolean){

            val currentDate: String =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            var products= ShoppingCart.getProducts()

            reservationJsonobj.put("total", total.toString())
            reservationJsonobj.put("status", "pendiente")
            reservationJsonobj.put("date", currentDate)
            if(checkGuest){
                reservationJsonobj.put("id_customer", 1)
            }else{
                reservationJsonobj.put("id_customer", ShoppingCart.getCustomer().id)
            }


            for(product in products){
                productsJsonArray.put(product.id)
            }
            reservationJsonobj.put("id_products", productsJsonArray)
        }

    }
}