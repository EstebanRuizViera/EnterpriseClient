package com.example.enterpriseclient

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


class RequestHttp {

    companion object {
        private var db: ReservationDatabase? = null
        const val URL ="http://192.168.103.210:8000"


        //------------------- User, login and register -----------------------

        @JvmStatic fun login(context: Context) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL +"/auth/login"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, null,
                Response.Listener {
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Identificacion erronea ", Toast.LENGTH_SHORT).show()
                }
            ){}
            queue.add(req)
        }

        @JvmStatic fun registerUser(context: Context) {


            val queue = Volley.newRequestQueue(context)
            val url = URL +"/auth/register"
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

//        @JvmStatic fun logout(context: Context,usersViewModel: UsersViewModel){
//            usersViewModel.updateToken(User(1,"",""))
//            val intent = Intent(context,LoginActivity::class.java)
//            context.startActivity(intent)
//        }

        @JvmStatic fun updateToken(context: Context) {


            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/user"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, null,
                Response.Listener {

                },
                Response.ErrorListener {
                    Log.println(Log.INFO,null,"ERROR "+it.message)
                }
            ){}
            queue.add(req)
        }




        //------------- PRODUCTOS --------------------

        @JvmStatic fun createProduct(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/products"
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


        @JvmStatic fun selectProduct(context: Context) {


            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/products/"
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

        fun selectAllProducts(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/products"
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

        @JvmStatic fun updateProduct(context:Context) {

            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/products/"
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

        @JvmStatic fun deleteProduct(context: Context) {

            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/products/"
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


        //------------- AVAILABILITY --------------------

        @JvmStatic fun createAvailability(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/availability"
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


        @JvmStatic fun selectAvailability(context: Context) {


            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/availability/"
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

        fun selectAllAvailability(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/availability"
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

        @JvmStatic fun updateAvailability(context:Context) {

            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/availability/"
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

        @JvmStatic fun deleteAvailability(context: Context) {

            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/availability/"
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

        //------------- DISTRIBUTION --------------------

        @JvmStatic fun createDistribution(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/distribution"
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


        @JvmStatic fun selectDistribution(context: Context) {


            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/distribution/"
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

        fun selectAllDistribution(context: Context){

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/distribution"
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

        @JvmStatic fun updateDistribution(context:Context) {

            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/distribution/"
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

        @JvmStatic fun deleteDistribution(context: Context) {

            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/distribution/"
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

        @JvmStatic fun updateReservation(context:Context) {

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