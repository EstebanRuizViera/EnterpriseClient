package com.example.enterpriseclient.requestServer

import android.content.Context
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
                    Toast.makeText(context, "Error al devolver los vuelos", Toast.LENGTH_SHORT)
                        .show()
                }
            ) {}

            queue.add(updateReq)

        }

//        @JvmStatic
//        fun createDistribution(context: Context, usersViewModel: UsersViewModel) {
//
//            // new Volley newRequestQueue
//            val queue = Volley.newRequestQueue(context)
//            val url = Constants.URL_SERVER + "/api/distribution"
//            val updateReq = object : StringRequest(
//                Request.Method.POST, url,
//                Response.Listener {
//
//                },
//                Response.ErrorListener {
//                    Toast.makeText(context, "Error al crear la reserva", Toast.LENGTH_SHORT).show()
//                }
//            )
//            {
//                @Throws(AuthFailureError::class)
//                override fun getHeaders(): Map<String, String> {
//                    val headers: MutableMap<String, String> =
//                        HashMap()
//                    // Basic Authentication
//                    var token = usersViewModel.getToken(1)
//                    headers["Authorization"] = "Bearer "+token
//                    return headers
//                }
//            }
//
//            queue.add(updateReq)
//
//        }
//
//
//        @JvmStatic
//        fun selectDistribution(context: Context, usersViewModel: UsersViewModel) {
//
//
//            val queue = Volley.newRequestQueue(context)
//            val url = Constants.URL_SERVER + "/api/distribution/"
//            val req = object : JsonObjectRequest(
//                Request.Method.GET, url, null,
//                Response.Listener {
//
//                },
//                Response.ErrorListener {
//                    Log.println(Log.INFO, null, "ERROR " + it.message)
//                })
//            {
//                @Throws(AuthFailureError::class)
//                override fun getHeaders(): Map<String, String> {
//                    val headers: MutableMap<String, String> =
//                        HashMap()
//                    // Basic Authentication
//                    var token = usersViewModel.getToken(1)
//                    headers["Authorization"] = "Bearer "+token
//                    return headers
//                }
//            }
//
//            queue.add(req)
//        }
//

//
//        @JvmStatic
//        fun updateDistribution(context: Context, usersViewModel: UsersViewModel) {
//
//            val queue = Volley.newRequestQueue(context)
//            val url = Constants.URL_SERVER + "/api/distribution/"
//            val req = object : JsonObjectRequest(
//                Request.Method.PUT, url, null,
//                Response.Listener {
//                },
//                Response.ErrorListener {
//                    Log.println(Log.INFO, null, "ERROR " + it.message)
//                })
//            {
//                @Throws(AuthFailureError::class)
//                override fun getHeaders(): Map<String, String> {
//                    val headers: MutableMap<String, String> =
//                        HashMap()
//                    // Basic Authentication
//                    var token = usersViewModel.getToken(1)
//                    headers["Authorization"] = "Bearer "+token
//                    return headers
//                }
//            }
//
//            queue.add(req)
//        }
//
//        @JvmStatic
//        fun deleteDistribution(context: Context, usersViewModel: UsersViewModel) {
//
//            val queue = Volley.newRequestQueue(context)
//            val url = Constants.URL_SERVER + "/api/distribution/"
//            val req = object : StringRequest(
//                Request.Method.DELETE, url,
//                Response.Listener {
//
//                },
//                Response.ErrorListener {
//                    Log.println(Log.INFO, null, "ERROR " + it.toString())
//                })
//            {
//                @Throws(AuthFailureError::class)
//                override fun getHeaders(): Map<String, String> {
//                    val headers: MutableMap<String, String> =
//                        HashMap()
//                    // Basic Authentication
//                    var token = usersViewModel.getToken(1)
//                    headers["Authorization"] = "Bearer "+token
//                    return headers
//                }
//            }
//
//            queue.add(req)
//        }
    }
}