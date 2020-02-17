package com.example.enterpriseclient.requestServer

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.StringRequest
import com.example.enterpriseclient.MainActivity
import com.example.enterpriseclient.bottomNavigationView.user.LoginFragment
import com.example.enterpriseclient.bottomNavigationView.user.UserFragment
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase
import com.example.enterpriseclient.myDataBase.model.User
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import org.json.JSONArray
import org.json.JSONObject

class RequestUser {
    companion object {

        private var db: ReservationDatabase? = null
        const val URL = "http://192.168.103.210:8000"


        //------------------- User, login and register -----------------------


        @JvmStatic
        fun login(
            activity: MainActivity,
            context: Context,
            email_text: EditText,
            password_text: EditText,
            usersViewModel: UsersViewModel
        ) {

            val loginJsonobj = JSONObject()

            loginJsonobj.put("email", email_text.text)
            loginJsonobj.put("password", password_text.text)

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL + "/api/login"
            val req = object : JsonObjectRequest(Request.Method.POST, url, loginJsonobj,
                Response.Listener {
                    updateToken(
                        activity,
                        context,
                        email_text,
                        usersViewModel,
                        it.getString("token")
                    )
                    Toast.makeText(context, "Access granted ", Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Access denied ", Toast.LENGTH_SHORT).show()
                }
            ) {}
            queue.add(req)
        }


        @JvmStatic
        fun updateToken(
            activity: MainActivity,
            context: Context,
            email_text: EditText,
            usersViewModel: UsersViewModel,
            token: String
        ) {

            val jsonArray = JSONArray()
            val jsonObject = JSONObject()

            jsonObject.put("email", email_text.text.toString())
            jsonArray.put(jsonObject)
            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL + "/api/user"
            val req = object : JsonArrayRequest(Request.Method.POST, url, jsonArray,
                Response.Listener {
                    Log.println(Log.INFO, null, "ERROR " + it.toString())
                    usersViewModel.updateUser(
                        User(
                            1,
                            it.getJSONObject(0).getString("id"),
                            it.getJSONObject(0).getString("name"),
                            it.getJSONObject(0).getString("email"),
                            token
                        )
                    )
                    val fragment = UserFragment.newInstance()
                    activity.openFragment(fragment)

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                }
            ) {}

            queue.add(req)
        }


        @JvmStatic
        fun registerUser(
            activity: MainActivity,
            context: Context,
            rg_name: EditText,
            rg_email: EditText,
            rg_password: EditText
        ) {

            val loginJsonobj = JSONObject()

            loginJsonobj.put("name", rg_name.text)
            loginJsonobj.put("email", rg_email.text)
            loginJsonobj.put("password", rg_password.text)

            val queue = Volley.newRequestQueue(context)
            val url = RequestUser.URL + "/api/register"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, loginJsonobj,
                Response.Listener {

                    Toast.makeText(context, "Account created ", Toast.LENGTH_LONG).show()
                    val fragment = LoginFragment.newInstance()
                    activity.openFragment(fragment)
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Account not created. Try again later ", Toast.LENGTH_LONG).show()
                }) {}

            queue.add(req)
        }


        @JvmStatic
        fun createReservation(
            context: Context,
            total: Int,
            date: String,
            id_customer: String,
            id_product: String
        ) {

            val reservationJsonobj = JSONObject()
            val productsJsonArray = JSONArray()

            reservationJsonobj.put("total", total.toString())
            reservationJsonobj.put("status", "pendiente")
            reservationJsonobj.put("date", "2020-10-10")
            reservationJsonobj.put("id_customer", id_customer)

            productsJsonArray.put(id_product)

            reservationJsonobj.put("id_products", productsJsonArray)

            val queue = Volley.newRequestQueue(context)
            val url = RequestUser.URL + "/createReservation"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, reservationJsonobj,
                Response.Listener {
                    Toast.makeText(
                        context,
                        "" + it.getString("state"),
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Registro no realizado !", Toast.LENGTH_LONG).show()
                    Log.println(Log.INFO, null, "ERROR Volley " + reservationJsonobj.toString())
                }) {}

            queue.add(req)
        }

        @JvmStatic
        fun updateUser(
            activity: MainActivity,
            usersViewModel: UsersViewModel,
            name_editText: TextView,
            email_editText: TextView,
            data_user_local:List<User>
        ) {

            val updateJsonobj = JSONObject()

            updateJsonobj.put("name", name_editText.text)
            updateJsonobj.put("email", email_editText.text)

            val queue = Volley.newRequestQueue(activity)
            val url = URL + "/auth/users/" + usersViewModel.getUserId(1)
            val req = object : JsonObjectRequest(
                Request.Method.PUT, url, updateJsonobj,
                Response.Listener {
                    usersViewModel.updateUser(User(1, data_user_local.get(0).id_remoto, name_editText.text.toString(), email_editText.text.toString(), data_user_local.get(0).token))
                    Toast.makeText(activity, "actualización realizada con exito", Toast.LENGTH_LONG)
                        .show()

                    val fragment = UserFragment.newInstance()
                    activity.openFragment(fragment)

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.message)
                }) {
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

            queue.add(req)
        }

        @JvmStatic
        fun deleteUser(context: Context, usersViewModel: UsersViewModel) {

            val queue = Volley.newRequestQueue(context)
            val url = URL + "/auth/users/" + usersViewModel.getUserId(1)
            val req = object : StringRequest(
                Request.Method.DELETE, url,
                Response.Listener {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    Log.println(Log.INFO, null, "delete ")
                    logout(context, usersViewModel)
                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "ERROR " + it.toString())
                }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> =
                        HashMap()
                    // Basic Authentication
                    var token = usersViewModel.getToken(1)
                    Log.println(Log.INFO, null, "ERROR " + token)
                    headers["Authorization"] = "Bearer " + token
                    return headers
                }
            }

            queue.add(req)
        }

        @JvmStatic
        fun logout(context: Context, usersViewModel: UsersViewModel) {
            usersViewModel.updateUser(User(1, "", "You are not logged in", "Login", "Register"))
            Log.println(Log.INFO, null, "log_out ")
            val intent = Intent(context,MainActivity::class.java)
            context.startActivity(intent)
        }

    }
}