package com.example.enterpriseclient.requestServer

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
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
import com.example.enterpriseclient.adapter.CartAdapter
import com.example.enterpriseclient.model.ProductPojo
import com.example.enterpriseclient.adapter.ProductAdapter
import com.example.enterpriseclient.adapter.UserAdapter
import com.example.enterpriseclient.model.ProductProfilePojo
import com.example.enterpriseclient.mySynchronized.SynchronizedLocalDatabase
import kotlinx.android.synthetic.main.activity_cart_list.*

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
                    Log.println(Log.INFO, null, "Error getting your product for id" )
                }) {}

            queue.add(req)
        }

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
                    Log.println(Log.INFO, null, "Error getting your bookings for customer")
                }
            ) {}

            queue.add(updateReq)

        }

        fun prueba(
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
                    val productAdapter =
                        CartAdapter(
                            context,
                            productListPojo
                        )
                    recyclerView.setAdapter(productAdapter)
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error getting your bookings for customer")
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
                    Log.println(Log.INFO, null, "Error getting your bookings")
                    synchronizedLocalDatabase.getLocalAvailability()
                    synchronizedLocalDatabase.getRemoteAvailability()
                }
            ) {}

            queue.add(updateReq)


        }
    }
}