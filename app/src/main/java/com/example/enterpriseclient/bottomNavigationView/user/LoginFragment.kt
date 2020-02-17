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
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestUser

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var productViewModel: ProductViewModel

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        productViewModel = run {
            ViewModelProviders.of(this).get(ProductViewModel::class.java)
        }

        var lg_login = root.findViewById<TextView>(R.id.lg_login)
        var lg_loginText = root.findViewById<TextView>(R.id.lg_loginText)
        var lg_email = root.findViewById<EditText>(R.id.lg_email)
        var lg_password = root.findViewById<EditText>(R.id.lg_password)

        lg_login.setOnClickListener() {
            RequestUser.login(activity as MainActivity,root.context, lg_email, lg_password, usersViewModel)
//            val fragment = LoginFragment.newInstance()
//            val activity = activity as MainActivity
//            activity.openFragment(fragment)
        }

        lg_loginText.setOnClickListener() {
//            val intent = Intent(root.context, RegisterActivity::class.java)
//            startActivity(intent)
            val fragment = RegisterFragment.newInstance()
            val activity = activity as MainActivity
            activity.openFragment(fragment)
        }

        return root
    }
}

