package com.example.enterpriseclient.requestServer

import android.content.Context
import android.content.Intent
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.enterpriseclient.Constants
import com.example.enterpriseclient.MainActivity
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
            val url = Constants.URL_SERVER + "/createReservation"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, reservationJsonobj,
                Response.Listener {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error creating your reservation. Try again later")
                }) {}

            queue.add(req)
        }

    }
}