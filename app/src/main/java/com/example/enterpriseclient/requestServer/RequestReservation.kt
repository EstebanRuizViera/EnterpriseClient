package com.example.enterpriseclient.requestServer

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase

class RequestReservation {
    companion object {
        private var db: ReservationDatabase? = null
        const val URL ="http://192.168.103.210:8000"


        //------------- RESERVATION --------------------

        @JvmStatic fun createrReservation(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/reservation"
            val updateReq = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener {

                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al crear la reserva", Toast.LENGTH_SHORT).show()
                }
            ){}

            queue.add(updateReq)

        }


        @JvmStatic fun selectReservation(context: Context) {


            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/reservation/"
            val req = object : JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener {

                },
                Response.ErrorListener {
                    Log.println(Log.INFO,null,"ERROR "+it.message)
                })
            {}

            queue.add(req)
        }

        fun selectAllReservation(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/reservation"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {

                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al devolver los vuelos", Toast.LENGTH_SHORT).show()
                }
            ){}

            queue.add(updateReq)

        }

        @JvmStatic fun updateReservation(context: Context) {

            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/reservation/"
            val req = object : JsonObjectRequest(
                Request.Method.PUT, url, null,
                Response.Listener {
                },
                Response.ErrorListener {
                    Log.println(Log.INFO,null,"ERROR "+it.message)
                })
            {}

            queue.add(req)
        }

        @JvmStatic fun deleteReservation(context: Context) {

            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/reservation/"
            val req = object : StringRequest(
                Request.Method.DELETE, url,
                Response.Listener {

                },
                Response.ErrorListener {
                    Log.println(Log.INFO,null,"ERROR "+it.toString())
                })
            {}

            queue.add(req)
        }


    }
}