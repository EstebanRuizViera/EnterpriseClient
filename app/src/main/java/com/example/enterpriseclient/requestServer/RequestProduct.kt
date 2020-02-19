package com.example.enterpriseclient.requestServer

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.enterpriseclient.Constants
import com.example.enterpriseclient.R
import com.example.enterpriseclient.model.ProductPojo
import com.example.enterpriseclient.adapter.ProductAdapter
import com.example.enterpriseclient.adapter.UserAdapter
import com.example.enterpriseclient.model.ProductProfilePojo
import com.example.enterpriseclient.mySynchronized.SynchronizedLocalDatabase

class RequestProduct {

    companion object {

        //------------- PRODUCTS --------------------

        @JvmStatic
        fun selectProduct(context: Context, productName : TextView, productDescription : TextView, id : String, img:ImageView) {

            var option = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape)

            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/api/products/" +id
            val req = object : JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener {

                    productName.setText(it.getString("name"))
                    productDescription.setText(it.getString("description"))
                        Glide.with(context).load(it.getString("img")).apply(option)
                            .into(img)

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                }) {}

            queue.add(req)
        }


//        fun selectAllProducts(
//            context: Context,
//            productPojoList: ArrayList<ProductPojo>,
//            recyclerView: RecyclerView
//        ) {
//
//            // new Volley newRequestQueue
//            val queue = Volley.newRequestQueue(context)
//            val url = Constants.URL_SERVER + "/api/products"
//            val updateReq = object : JsonArrayRequest(
//                Request.Method.GET, url, null,
//                Response.Listener {
//                    var array = it
//                    for (i in 0 until array.length()) {
//                        val product = array.getJSONObject(i)
//                        productPojoList.add(
//                            ProductPojo(
//                                product.getInt("id"),
//                                product.getString("name"),
//                                product.getString("description"),
//                                product.getString("img"),
//                                0
//                            )
//                        )
//
//                    }
//                    //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
//                    val productAdapter =
//                        ProductAdapter(
//                            context,
//                            productPojoList
//                        )
//                    recyclerView.setAdapter(productAdapter)
//                },
//                Response.ErrorListener {
//                    Toast.makeText(context, "Error getting the products. Try again later", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            ) {}
//
//            queue.add(updateReq)
//
//        }


        fun selectAllProductsForCustomer(
            context: Context,
            productListPojo: ArrayList<ProductProfilePojo>,
            recyclerView: RecyclerView,
            id: String
        ) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/allProductsForCustomer/"+id
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    Log.println(Log.INFO, null, "Llego al listener:")
                    var array = it
                    for (i in 0 until array.length()) {
                        val product = array.getJSONObject(i)
                        productListPojo.add(
                            ProductProfilePojo(
                                product.getString("name"),
                                product.getString("date"),
                                product.getString("status"),
                                "120",
                                product.getString("img")
                            )
                        )

                    }
                    //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
                    val userAdapter =
                        UserAdapter(
                            context,
                            productListPojo
                        )
                    recyclerView.setAdapter(userAdapter)
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Llego al error: "+it.message)
                    Toast.makeText(context, "Error getting your bookings. Try again later", Toast.LENGTH_SHORT)
                        .show()
                }
            ) {}

            queue.add(updateReq)

        }

        fun selectAllProducts(
            context: Context,
            productPojoList: ArrayList<ProductPojo>,
            synchronizedLocalDatabase: SynchronizedLocalDatabase
        ) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/api/products"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    var array = it
                    for (i in 0 until array.length()) {
                        val product = array.getJSONObject(i)
                        productPojoList.add(
                            ProductPojo(
                                product.getInt("id"),
                                product.getString("name"),
                                product.getString("description"),
                                product.getString("img"),
                                0
                            )
                        )
                    }
                    synchronizedLocalDatabase.saveProduct()
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error getting the products. Try again later", Toast.LENGTH_SHORT)
                        .show()
                }
            ) {}

            queue.add(updateReq)

        }

//        @JvmStatic
//        fun countProducts(context: Context,sn: SynchronizeThread) {
//
//
//            // new Volley newRequestQueue
//            val queue = Volley.newRequestQueue(context)
//            val url = Constants.URL_SERVER + "/api/countProducts"
//            val updateReq = object : StringRequest(
//                Request.Method.GET, url,
//                Response.Listener {
//                    Log.println(Log.INFO, null, "Total volley: " + it)
//                },
//                Response.ErrorListener {
//                    Toast.makeText(context, "Error al contar las filas", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            ) {}
//
//            queue.add(updateReq)
//        }
    }
}