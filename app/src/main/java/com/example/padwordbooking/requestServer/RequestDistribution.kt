package com.example.padwordbooking.requestServer

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.padwordbooking.Constants
import com.example.padwordbooking.model.Distribution

class RequestDistribution {
    companion object {


        //------------- DISTRIBUTION --------------------

        fun selectAllDistribution(
            context: Context, distributions: ArrayList<Distribution>
        ) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/api/distribution"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    var array = it
                    for (i in 0 until array.length()) {
                        val distribution = array.getJSONObject(i)
                        distributions.add(
                            Distribution(
                                distribution.getInt("id"),
                                distribution.getString("unit"),
                                distribution.getInt("duration"),
                                "",
                                "",
                                distribution.getInt("block")
                            )
                        )
                    }
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error getting your distributions")
                }
            ) {}

            queue.add(updateReq)

        }

    }
}