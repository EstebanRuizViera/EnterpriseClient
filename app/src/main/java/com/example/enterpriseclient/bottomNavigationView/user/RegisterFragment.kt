package com.example.enterpriseclient.bottomNavigationView.user


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.enterpriseclient.MainActivity
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

        val root = inflater.inflate(R.layout.activity_register, container, false)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        var rg_register = root.findViewById<TextView>(R.id.rg_register)
        var rg_registerText = root.findViewById<TextView>(R.id.rg_registerText)

        var rg_name = root.findViewById<EditText>(R.id.rg_name)
        var rg_email = root.findViewById<EditText>(R.id.rg_email)
        var rg_password = root.findViewById<EditText>(R.id.rg_password)

        rg_register.setOnClickListener(){
            RequestUser.registerUser(activity as MainActivity,root.context,rg_name,rg_email,rg_password)
        }

        rg_registerText.setOnClickListener {
            val fragment = LoginFragment.newInstance()
            val activity = activity as MainActivity
            activity.openFragment(fragment)
        }

        return root
    }


}
