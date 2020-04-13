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
import com.example.padwordbooking.Constants
import com.example.padwordbooking.DrawerActivity
import org.json.JSONArray
import org.json.JSONObject

class RequestReservation {
    companion object {


        //------------- RESERVATION --------------------

        @JvmStatic
        fun createReservation(
            context: Context,
            total: Int,
            date: String,
            id_customer: String,
            id_product: String
        ) {

            val reservationJsonobj = JSONObject()
            val productsJsonArray = JSONArray()

            reservationJsonobj.put("total", total.toString())
            reservationJsonobj.put("status", "pendiente")
            reservationJsonobj.put("date", date)
            reservationJsonobj.put("id_customer", id_customer)

            productsJsonArray.put(id_product)

            reservationJsonobj.put("id_products", productsJsonArray)

            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/api/createReservations"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, reservationJsonobj,
                Response.Listener {
                    val intent = Intent(context, DrawerActivity::class.java)
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

    }
}