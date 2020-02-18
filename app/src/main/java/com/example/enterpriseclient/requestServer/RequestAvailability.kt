package com.example.enterpriseclient.requestServer

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.enterpriseclient.Constants
import com.example.enterpriseclient.adapter.AvailabilityAdapter
import com.example.enterpriseclient.model.AvailabilityPojo
import com.example.enterpriseclient.mySynchronized.SynchronizedLocalDatabase

class RequestAvailability {
    companion object {


        //------------- AVAILABILITY --------------------

        @JvmStatic
        fun selectAvailabilityForProduct(
            context: Context, availabilityPojos: ArrayList<AvailabilityPojo>,
            recyclerView: RecyclerView, id: String
        ) {

            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/select_availabilities/" + id
            val req = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    var array = it
                    for (i in 0 until array.length()) {
                        val availability = array.getJSONObject(i)

                        availabilityPojos.add(
                            AvailabilityPojo(
                                availability.getInt("id"),
                                availability.getString("timestamp"),
                                availability.getString("timestamp"),
                                availability.getDouble("price") ,
                                availability.getDouble("quota") ,
                                availability.getInt("id_product")

                            )
                        )

                    }
                    //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
                    val availabilityAdapter =
                        AvailabilityAdapter(
                            context,
                            availabilityPojos
                        )
                    recyclerView.setAdapter(availabilityAdapter)
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                }) {}

            queue.add(req)
        }

        @JvmStatic
        fun selectAvailabilityForProduct(
            context: Context, availabilityPojos: ArrayList<AvailabilityPojo>,
            synchronizedLocalDatabase: SynchronizedLocalDatabase

        ) {

            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/api/availability"
            val req = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    var array = it
                    for (i in 0 until array.length()) {
                        val availability = array.getJSONObject(i)
                        availabilityPojos.add(
                            AvailabilityPojo(
                                availability.getInt("id"),
                                availability.getString("timestamp"),
                                availability.getString("timestamp"),
                                availability.getDouble("price") ,
                                availability.getDouble("quota") ,
                                availability.getInt("id_product")
                            )
                        )
                    }
                    synchronizedLocalDatabase.saveAvailability()
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                }) {}

            queue.add(req)
        }
    }
}