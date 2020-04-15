package com.example.padwordbooking.requestServer

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.padwordbooking.Constants
import com.example.padwordbooking.R
import com.example.padwordbooking.adapter.ProductListAdapter
import com.example.padwordbooking.adapter.UserAdapter
import com.example.padwordbooking.model.Availability
import com.example.padwordbooking.model.Distribution
import com.example.padwordbooking.model.Product
import com.example.padwordbooking.model.ProductProfile
import com.example.padwordbooking.myDataBase.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.activity_drawer.*
import org.json.JSONArray
import kotlin.math.roundToInt

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
            recyclerViewHome:RecyclerView
        ) {
            var productsList = arrayListOf<Product>()
            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(activity.applicationContext)
            val url = Constants.URL_SERVER + "/api/product_with_distribution"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    getAvailabilitiesForProduct(productsList,it)
                    setRecyclerViewDrawerActicity(activity,productsList,recyclerViewHome)
                },
                Response.ErrorListener {
                    Toast.makeText(activity,"Error getting your product",Toast.LENGTH_SHORT).show()
                    activity.swipeRefreshLayout.isRefreshing = false
                    activity.swipeRefreshLayout.isEnabled = false
                }
            ) {}

            queue.add(updateReq)
        }

        fun setRecyclerViewDrawerActicity(activity: Activity, productList: ArrayList<Product>, recyclerViewHome:RecyclerView){
            val productAdapter =
                ProductListAdapter(
                    activity,
                    productList
                )

            val layoutManagerProducts = GridLayoutManager(activity, 2)
            recyclerViewHome.layoutManager = layoutManagerProducts
            recyclerViewHome.adapter = productAdapter
            activity.swipeRefreshLayout.isRefreshing = false
            activity.swipeRefreshLayout.isEnabled = false
        }

        fun getAvailabilitiesForProduct(productsList:ArrayList<Product>,arrayProduct:JSONArray){
            var arrayAvailability = arrayListOf<Availability>()

            for (i in 0 until arrayProduct.length()) {
                val product = arrayProduct.getJSONObject(i)
                val availabilities = product.getJSONArray("availabilities")

                var temporalArrayAvailabilities = arrayListOf<Availability>()
                for (j in 0 until availabilities.length()) {

                    val availability = availabilities.getJSONObject(j)
                    var timestamp = availability.getString("timestamp").split(" ")

                    temporalArrayAvailabilities.add( Availability(
                        availability.getInt("id"),
                        timestamp[0],
                        timestamp[1],
                        availability.getDouble("price"),
                        availability.getDouble("quota"),
                        product.getInt("id")
                    ))
                }
                arrayAvailability = temporalArrayAvailabilities
                var distribution:Distribution
                if(product.getJSONArray("distribution").length()!=0){
                    var tempDistribution = product.getJSONArray("distribution").getJSONObject(0)
                    distribution= Distribution(
                        tempDistribution.getInt("id"),
                        tempDistribution.getString("unit"),
                        tempDistribution.getInt("duration"),
                        tempDistribution.getString("time_start"),
                        tempDistribution.getString("time_finish"),
                        tempDistribution.getInt("block"))
                }else{
                    distribution = Distribution(0,"",123,"","",10)
                }

                var price = temporalArrayAvailabilities[0].price.roundToInt()
                productsList.add(
                    Product(
                        product.getInt("id"),
                        product.getString("name"),
                        product.getString("description"),
                        price.toString(),
                        Constants.URL_SERVER + product.getString("img"),
                        distribution,
                        arrayAvailability
                    )
                )

            }
        }
    }
}