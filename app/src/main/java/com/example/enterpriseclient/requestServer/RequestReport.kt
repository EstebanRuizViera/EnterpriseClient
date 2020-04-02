package com.example.enterpriseclient.requestServer

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.enterpriseclient.Constants

class RequestReport {
    companion object {


        //------------- AVAILABILITY --------------------

        @JvmStatic
        fun generateReportListProduct(
            context: Context
        ) {

            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/report"
                val req = object : StringRequest(
                Request.Method.GET, url,
                Response.Listener {
                    Log.println(Log.INFO, null, it)
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error generate your document"+it )
                }) {}

            queue.add(req)
        }

    }
}