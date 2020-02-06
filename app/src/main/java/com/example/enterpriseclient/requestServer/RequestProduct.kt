package com.example.enterpriseclient.requestServer

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel

class RequestProduct {

    companion object {
        private var db: ReservationDatabase? = null
        const val URL = "http://192.168.103.210:8000"

        //------------- PRODUCTOS --------------------

        @JvmStatic
        fun createProduct(context: Context,usersViewModel:UsersViewModel) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url =URL + "/auth/products"
            val updateReq = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener {

                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al crear la reserva", Toast.LENGTH_SHORT).show()
                }
            )
            {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> =
                        HashMap()
                    // Basic Authentication
                    var token = usersViewModel.getToken(1)
                    headers["Authorization"] = "Bearer "+token
                    return headers
                }
            }

            queue.add(updateReq)

        }


        @JvmStatic
        fun selectProduct(context: Context,usersViewModel:UsersViewModel) {


            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/products/1"
            val req = object : JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    Log.println(Log.INFO, null, "Name: " + it.getString("name")+" description: "+it.getString("description"))
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                })
            {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> =
                        HashMap()
                    // Basic Authentication
                    var token = usersViewModel.getToken(1)
                    headers["Authorization"] = "Bearer "+token
                    return headers
                }
            }

            queue.add(req)
        }

        fun selectAllProducts(context: Context,usersViewModel:UsersViewModel) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/products"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {

                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al devolver los vuelos", Toast.LENGTH_SHORT)
                        .show()
                }
            )
            {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> =
                        HashMap()
                    // Basic Authentication
                    var token = usersViewModel.getToken(1)
                    headers["Authorization"] = "Bearer "+token
                    return headers
                }
            }

            queue.add(updateReq)

        }

        @JvmStatic
        fun updateProduct(context: Context,usersViewModel:UsersViewModel) {

            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/products/"
            val req = object : JsonObjectRequest(
                Request.Method.PUT, url, null,
                Response.Listener {
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                })
            {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> =
                        HashMap()
                    // Basic Authentication
                    var token = usersViewModel.getToken(1)
                    headers["Authorization"] = "Bearer "+token
                    return headers
                }
            }

            queue.add(req)
        }

        @JvmStatic
        fun deleteProduct(context: Context,usersViewModel:UsersViewModel) {

            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/products/"
            val req = object : StringRequest(
                Request.Method.DELETE, url,
                Response.Listener {

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.toString())
                })
            {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> =
                        HashMap()
                    // Basic Authentication
                    var token = usersViewModel.getToken(1)
                    headers["Authorization"] = "Bearer "+token
                    return headers
                }
            }

            queue.add(req)
        }
    }
}