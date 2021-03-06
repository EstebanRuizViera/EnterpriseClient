package com.example.enterpriseclient.fragment.user


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.enterpriseclient.ProfileActivity
import com.example.enterpriseclient.R
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestUser

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel


    companion object {
        fun newInstance(): RegisterFragment = RegisterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_register, container, false)

        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        var rg_register = root.findViewById<TextView>(R.id.rg_register)
        var rg_registerText = root.findViewById<TextView>(R.id.rg_registerText)

        var rg_name = root.findViewById<EditText>(R.id.rg_name)
        var rg_email = root.findViewById<EditText>(R.id.rg_email)
        var rg_password = root.findViewById<EditText>(R.id.rg_password)

        var rg_validate_name = root.findViewById<TextView>(R.id.rg_validate_name)
        var rg_validate_email = root.findViewById<TextView>(R.id.rg_validate_email)
        var rg_validate_password = root.findViewById<TextView>(R.id.rg_validate_password)

        var check = true

        rg_register.setOnClickListener(){
            if(rg_name.text.toString().equals("")){
                rg_validate_name.visibility=View.VISIBLE
                check = false
            }else{
                rg_validate_name.visibility=View.INVISIBLE
                check = true
            }
            if(rg_email.text.toString().equals("")){
                rg_validate_email.visibility=View.VISIBLE
                check = false
            }else{
                rg_validate_email.visibility=View.INVISIBLE
                check = true
            }
            if(rg_password.text.toString().equals("")){
                rg_validate_password.visibility=View.VISIBLE
                check = false
            }else{
                rg_validate_password.visibility=View.INVISIBLE
                check = true
            }
            rg_password

            if(check) {
                RequestUser.registerUser(activity as ProfileActivity,root.context,rg_name,rg_email,rg_password)
            }
        }

        rg_registerText.setOnClickListener {
            val fragment = LoginFragment.newInstance()
            val activity = activity as ProfileActivity
            activity.openFragment(fragment)
        }

        return root
    }


}
