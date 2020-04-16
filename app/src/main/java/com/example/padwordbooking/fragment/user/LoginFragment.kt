package com.example.padwordbooking.fragment.user


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.padwordbooking.ProfileActivity
import com.example.padwordbooking.R
import com.example.padwordbooking.myDataBase.viewModel.UsersViewModel
import com.example.padwordbooking.requestServer.RequestUser

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        var lg_login = root.findViewById<TextView>(R.id.lg_login)
        var lg_loginText = root.findViewById<TextView>(R.id.lg_loginText)

        var lg_email = root.findViewById<EditText>(R.id.lg_email)
        var lg_password = root.findViewById<EditText>(R.id.lg_password)

        var lg_validate_email = root.findViewById<TextView>(R.id.lg_validate_email)
        var lg_validate_password = root.findViewById<TextView>(R.id.lg_validate_password)


        var check :Boolean
        lg_login.setOnClickListener() {

            if(lg_email.text.toString().equals("")){
                lg_validate_email.visibility=View.VISIBLE
                check = false
            }else{
                lg_validate_email.visibility=View.INVISIBLE
                check = true
            }
            if(lg_password.text.toString().equals("")){
                lg_validate_password.visibility=View.VISIBLE
                check = false
            }else{
                lg_validate_password.visibility=View.INVISIBLE
                check = true
            }

            if(check) {
                RequestUser.login(activity as ProfileActivity, root.context, lg_email, lg_password, usersViewModel)
            }
        }

        lg_loginText.setOnClickListener() {
            val fragment = RegisterFragment.newInstance()
            val activity = activity as ProfileActivity
            activity.openFragment(fragment)
        }

        return root
    }
}

