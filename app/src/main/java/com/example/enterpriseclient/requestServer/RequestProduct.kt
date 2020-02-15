package com.example.enterpriseclient.requestServer

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.enterpriseclient.model.Product
import com.example.enterpriseclient.adapter.ProductAdapter
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel

class RequestProduct {

    companion object {
        private var db: ReservationDatabase? = null
        const val URL = "http://192.168.1.210:8000"

        //------------- PRODUCTOS --------------------

        @JvmStatic
        fun createProduct(context: Context, productViewModel: ProductViewModel) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/products"
            val updateReq = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener {

                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al crear la reserva", Toast.LENGTH_SHORT).show()
                }
            ) {}

            queue.add(updateReq)

        }


        @JvmStatic
        fun selectProduct(context: Context, productViewModel: ProductViewModel?, productName : TextView, productDescription : TextView) {


            val queue = Volley.newRequestQueue(context)
            val url = URL + "/api/products/1"
            val req = object : JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener {


                    productName.setText(it.getString("name"))
                    productDescription.setText(it.getString("description"))

//                    Log.println(
////                        Log.INFO,
////                        null,
////                        "Name: " + it.getString("name") + " description: " + it.getString("description")
////                    )

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                }) {}

            queue.add(req)
        }

        fun selectAllProducts(
            context: Context,
            productList: ArrayList<Product>,
            recyclerView: RecyclerView
        ) {

            var listProduct = arrayListOf<Product>()

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL + "/api/products"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    var array = it
                    for (i in 0 until array.length()) {
                        val product = array.getJSONObject(i)
                        productList.add(
                            Product(
                                0,
                                product.getString("name"),
                                product.getString("description"),
                                product.getString("img"),
                                0
                            )
                        )

                    }
                    //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
                    val productAdapter =
                        ProductAdapter(
                            context,
                            productList
                        )
                    recyclerView.setAdapter(productAdapter)
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al devolver los productos", Toast.LENGTH_SHORT)
                        .show()
                }
            ) {}

            queue.add(updateReq)

        }

        @JvmStatic
        fun updateProduct(context: Context, productViewModel: ProductViewModel) {

            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/products/"
            val req = object : JsonObjectRequest(
                Request.Method.PUT, url, null,
                Response.Listener {
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                }) {}

            queue.add(req)
        }

        @JvmStatic
        fun deleteProduct(context: Context, productViewModel: ProductViewModel) {

            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/products/"
            val req = object : StringRequest(
                Request.Method.DELETE, url,
                Response.Listener {

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.toString())
                }) {}

            queue.add(req)
        }

        @JvmStatic
        fun countProducts(context: Context,sn: SynchronizeThread) {


            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL + "/api/countProducts"
            val updateReq = object : StringRequest(
                Request.Method.GET, url,
                Response.Listener {
                    Log.println(Log.INFO, null, "Total volley: " + it)
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al contar las filas", Toast.LENGTH_SHORT)
                        .show()
                }
            ) {}

            queue.add(updateReq)
        }
    }
}