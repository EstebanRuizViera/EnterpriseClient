package com.example.padwordbooking.requestServer

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.padwordbooking.Constants
import com.example.padwordbooking.adapter.AvailabilityAdapter
import com.example.padwordbooking.model.Availability
import sun.bob.mcalendarview.MCalendarView
import sun.bob.mcalendarview.vo.DateData
import java.time.LocalDate
import kotlin.collections.ArrayList

class RequestAvailability {
    companion object {


        //------------- AVAILABILITY --------------------

        @JvmStatic
        fun selectAvailabilityForProduct(
            context: Context, availabilities: ArrayList<Availability>,
            recyclerView: RecyclerView, id: String, calendarView: MCalendarView
        ) {
            /*var newAvailabilityList = arrayListOf<String>()

            val queue = Volley.newRequestQueue(context)

            val url = Constants.URL_SERVER + "/api/select_availabilities/" + id

            val req = @SuppressLint("NewApi")
            object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    var array = it
                    for (i in 0 until array.length()) {
                        val availability = array.getJSONObject(i)
                        var timestamp = availability.getString("timestamp").split(" ")

                        var date:DateData = DateData(
                            Integer.parseInt(timestamp[0].split("-").get(0)),
                            Integer.parseInt(timestamp[0].split("-").get(1)),
                            Integer.parseInt(timestamp[0].split("-").get(2))
                        )
                        calendarView.markDate(date.year,date.month,date.day)

                        if(LocalDate.of(date.year,date.month,date.day) == LocalDate.now()) {

                            newAvailabilityList.add(timestamp[1])
                        }

                        availabilities.add(
                            Availability(
                                availability.getInt("id"),
                                timestamp[0],
                                timestamp[1],
                                availability.getDouble("price"),
                                availability.getDouble("quota"),
                                Integer.parseInt(id)
                            )
                        )
                        Log.println(Log.INFO, null, "date: "+timestamp[0]+" time: "+timestamp[1])

                    }
                    //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
                    val availabilityAdapter =
                        AvailabilityAdapter(
                            context,
                            newAvailabilityList
                        )
                    recyclerView.setAdapter(availabilityAdapter)
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error getting your availabilities")
                }) {}

            queue.add(req)*/
        }

        @JvmStatic
        fun selectAllAvailabilities(
            context: Context, availabilities: ArrayList<Availability>

        ) {

            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/api/availability"
            val req = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    var array = it
                    for (i in 0 until array.length()) {
                        val availability = array.getJSONObject(i)
                        availabilities.add(
                            Availability(
                                availability.getInt("id"),
                                availability.getString("timestamp"),
                                availability.getString("timestamp"),
                                availability.getDouble("price"),
                                availability.getDouble("quota"),
                                availability.getInt("id_product")
                            )
                        )
                    }
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error getting your availabilities"+it)
                }) {}

            queue.add(req)
        }
    }
}