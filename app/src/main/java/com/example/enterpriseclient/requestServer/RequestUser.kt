package com.example.enterpriseclient.requestServer

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase

class RequestUser {
    companion object {

        private var db: ReservationDatabase? = null
        const val URL = "http://192.168.103.210:8000"


        //------------------- User, login and register -----------------------

        @JvmStatic
        fun login(context: Context) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/login"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, null,
                Response.Listener {
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Identificacion erronea ", Toast.LENGTH_SHORT).show()
                }
            ) {}
            queue.add(req)
        }

        @JvmStatic
        fun registerUser(context: Context) {


            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/register"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, null,
                Response.Listener {
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.networkResponse)
                }) {}

            queue.add(req)
        }

//        @JvmStatic fun logout(context: Context,usersViewModel: UsersViewModel){
//            usersViewModel.updateToken(User(1,"",""))
//            val intent = Intent(context,LoginActivity::class.java)
//            context.startActivity(intent)
//        }

        @JvmStatic
        fun updateToken(context: Context) {


            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL + "/api/user"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, null,
                Response.Listener {

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                }
            ) {}
            queue.add(req)
        }
    }
}