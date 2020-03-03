package com.example.enterpriseclient.requestServer

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.enterpriseclient.Constants
import com.example.enterpriseclient.MainActivity
import com.example.enterpriseclient.bottomNavigationView.user.LoginFragment
import com.example.enterpriseclient.bottomNavigationView.user.UserFragment
import com.example.enterpriseclient.myDataBase.model.User
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import org.json.JSONArray
import org.json.JSONObject


class RequestUser {
    companion object {


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
            val url = Constants.URL_SERVER + "/api/login"
            val req = object : JsonObjectRequest(Request.Method.POST, url, loginJsonobj,
                Response.Listener {
                    updateToken(
                        activity,
                        context,
                        email_text,
                        usersViewModel,
                        it.getString("token")
                    )
                    var toast = Toast.makeText(context, "Access granted ", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
                    toast.show()
                },
                Response.ErrorListener {
                    var toast = Toast.makeText(context, "Access denied ", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
                    toast.show()
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
            val url = Constants.URL_SERVER + "/api/user"
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
//                    activity.openFragment(fragment)

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error updating token in local database ")
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
            val url = Constants.URL_SERVER + "/api/register"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, loginJsonobj,
                Response.Listener {

                    var toast=Toast.makeText(context, "Account created ", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
                    toast.show()
                    val fragment = LoginFragment.newInstance()
//                    activity.openFragment(fragment)
                },
                Response.ErrorListener {
                    var toast=Toast.makeText(
                        context,
                        "Account not created. Try again later ",
                        Toast.LENGTH_LONG
                    )
                    toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
                    toast.show()
                }) {}

            queue.add(req)
        }

        @JvmStatic
        fun updateUser(
            activity: MainActivity,
            usersViewModel: UsersViewModel,
            name_editText: TextView,
            email_editText: TextView,
            data_user_local: List<User>
        ) {

            val updateJsonobj = JSONObject()

            updateJsonobj.put("name", name_editText.text)
            updateJsonobj.put("email", email_editText.text)

            val queue = Volley.newRequestQueue(activity)
            val url = Constants.URL_SERVER + "/auth/users/" + usersViewModel.getUserId(1)
            val req = object : JsonObjectRequest(
                Request.Method.PUT, url, updateJsonobj,
                Response.Listener {
                    usersViewModel.updateUser(
                        User(
                            1,
                            data_user_local.get(0).id_remoto,
                            name_editText.text.toString(),
                            email_editText.text.toString(),
                            data_user_local.get(0).token
                        )
                    )
                    var toast=Toast.makeText(activity, "Successful update", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
                    toast.show()

                    val fragment = UserFragment.newInstance()
//                    activity.openFragment(fragment)

                },
                Response.ErrorListener {
                    Log.println(Log.INFO, null, "Error updating your user " )
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
            val url = Constants.URL_SERVER + "/auth/users/" + usersViewModel.getUserId(1)
            val req = object : StringRequest(
                Request.Method.DELETE, url,
                Response.Listener {
                    var toast=Toast.makeText(context, it, Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
                    toast.show()
                    usersViewModel.updateUser(User(1, "", "You are not logged in", "Login", "Register"))

                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                },
                Response.ErrorListener {
                    var toast=Toast.makeText(context, "Error deleting your account. Try again later", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
                    toast.show()
                    Log.println(Log.INFO, null, "Error deleting your account")
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
        fun logout(context: Context, usersViewModel: UsersViewModel) {
            usersViewModel.updateUser(User(1, "", "You are not logged in", "Login", "Register"))
            var toast = Toast.makeText(context, "Logged out", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
            toast.show()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }
}