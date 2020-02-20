package com.example.enterpriseclient.requestServer

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.enterpriseclient.Constants
import com.example.enterpriseclient.model.DistributionPojo
import com.example.enterpriseclient.mySynchronized.SynchronizedLocalDatabase

class RequestDistribution {
    companion object {


        //------------- DISTRIBUTION --------------------

        fun selectAllDistribution(
            context: Context, distributionPojos: ArrayList<DistributionPojo>,
            synchronizedLocalDatabase: SynchronizedLocalDatabase
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
                        distributionPojos.add(
                            DistributionPojo(
                                distribution.getInt("id"),
                                distribution.getString("unit"),
                                distribution.getInt("duration"),
                                1 ,
                                1,
                                distribution.getInt("block")
                            )
                        )
                    }
                    synchronizedLocalDatabase.saveDistribution()
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error getting your distribution. Try again later")
                }
            ) {}

            queue.add(updateReq)

        }

    }
}