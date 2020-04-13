package com.example.enterpriseclient.requestServer

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.enterpriseclient.Constants
import com.example.enterpriseclient.DrawerActivity
import com.example.enterpriseclient.R
import com.example.enterpriseclient.adapter.ProductAdapter
import com.example.enterpriseclient.adapter.UserAdapter
import com.example.enterpriseclient.model.Distribution
import com.example.enterpriseclient.model.Product
import com.example.enterpriseclient.model.ProductProfile
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.activity_drawer.*

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
                        Glide.with(context).load(Constants.URL_SERVER + it.getString("img")).apply(option)
                            .into(img)

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error getting your product for id" )
                }) {}

            queue.add(req)
        }

        fun selectAllProductsForCustomer(
            context: Context,
            usersViewModel: UsersViewModel,
            productList: ArrayList<ProductProfile>,
            recyclerView: RecyclerView,
            id: String
        ) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = Constants.URL_SERVER + "/auth/allProductsForCustomer/"+id
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    var array = it
                    for (i in 0 until array.length()) {
                        val product = array.getJSONObject(i)
                        productList.add(
                            ProductProfile(
                                product.getString("name"),
                                product.getString("date"),
                                product.getString("status"),
                                "120",
                                Constants.URL_SERVER + product.getString("img")
                            )
                        )

                    }
                    //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
                    val userAdapter =
                        UserAdapter(
                            context,
                            productList
                        )
                    recyclerView.setAdapter(userAdapter)
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error getting your bookings for customer")
                }
            ) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> =
                        HashMap()
                    // Basic Authentication
                    var token = usersViewModel.getToken(1)
                    headers["Authorization"] = "Bearer " + token
                    return headers
                }
            }

            queue.add(updateReq)

        }

        fun selectAllProducts(
            activity: Activity,
            productList: ArrayList<Product>,
            recyclerViewHome:RecyclerView
        ) {

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(activity.applicationContext)
            val url = Constants.URL_SERVER + "/api/products"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    var array = it
                    for (i in 0 until array.length()) {
                        val product = array.getJSONObject(i)
                        productList.add(
                            Product(
                                product.getInt("id"),
                                product.getString("name"),
                                product.getString("description"),
                                "100",
                                Constants.URL_SERVER + product.getString("img"),
                                Distribution(0,"",123,123,12,10)

                            )
                        )
                    }
                    val productAdapter =
                        ProductAdapter(
                            activity,
                            productList
                        )
                    recyclerViewHome.setAdapter(productAdapter)
                    activity.swipeRefreshLayout.isRefreshing = false
                    activity.swipeRefreshLayout.isEnabled = false
                },
                Response.ErrorListener {
                    Toast.makeText(activity,"Error getting your product",Toast.LENGTH_SHORT).show()
                    activity.swipeRefreshLayout.isRefreshing = false
                    activity.swipeRefreshLayout.isEnabled = false
                }
            ) {}

            queue.add(updateReq)


        }
    }
}