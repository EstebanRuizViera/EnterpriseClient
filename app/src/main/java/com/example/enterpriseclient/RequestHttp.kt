package com.example.appreservas

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appreservas.myDataBase.database.FlightDatabase


class RequestHttp {

    companion object {
        private var db: FlightDatabase? = null
        const val URL ="http://192.168.103.200:8000"

        @JvmStatic fun login(context: Context) {


            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/auth/login"
            val req = object : JsonObjectRequest(Request.Method.POST, url, null,
                Response.Listener {
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Identificacion erronea ", Toast.LENGTH_SHORT).show()
                }
            ){}
            queue.add(req)
        }

//        @JvmStatic fun logout(context: Context,usersViewModel: UsersViewModel){
//            usersViewModel.updateToken(User(1,"",""))
//            val intent = Intent(context,LoginActivity::class.java)
//            context.startActivity(intent)
//        }

        @JvmStatic fun updateToken(context: Context) {


            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/auth/user"
            val req = object : JsonObjectRequest(Request.Method.POST, url, null,
                Response.Listener {

                },
                Response.ErrorListener {
                    Log.println(Log.INFO,null,"ERROR "+it.message)
                }
            ){}
            queue.add(req)
        }


        @JvmStatic fun registerUser(context: Context) {


            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/auth/register"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, null,
                Response.Listener {
                },
                Response.ErrorListener {
                    Log.println(Log.INFO,null,"ERROR "+it.networkResponse)
                })
            {}

            queue.add(req)
        }

        @JvmStatic fun selectUser(context: Context) {


            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/user/"
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

        @JvmStatic fun updateUser(context:Context) {

            val queue = Volley.newRequestQueue(context)
            val req = object : JsonObjectRequest(
                Request.Method.PUT, null, null,
                Response.Listener {
                },
                Response.ErrorListener {
                    Log.println(Log.INFO,null,"ERROR "+it.message)
                })
            {}

            queue.add(req)
        }

        @JvmStatic fun deleteUser(context: Context) {

            val queue = Volley.newRequestQueue(context)
            val req = object : StringRequest(
                Request.Method.DELETE, null,
                Response.Listener {

                },
                Response.ErrorListener {
                    Log.println(Log.INFO,null,"ERROR "+it.toString())
                })
            {}

            queue.add(req)
        }

        fun createReservation(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)

            val updateReq = object : StringRequest(
                Request.Method.GET, null,
                Response.Listener {

                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al crear la reserva", Toast.LENGTH_SHORT).show()
                }
            ){}

            queue.add(updateReq)

        }

        fun getAllFlights(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/select_flight/"
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

        fun getAllReserver(context: Context){

            //REVISAR SERVIDOR PARA ESTA TABLA, DEBIDO A QUE NO EXITE ACCESO DESDE EL CLIENTE
            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/select_reservations/"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {

                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al obtener las reservas", Toast.LENGTH_SHORT).show()
                }
            ){}

            queue.add(updateReq)
        }

    }

}