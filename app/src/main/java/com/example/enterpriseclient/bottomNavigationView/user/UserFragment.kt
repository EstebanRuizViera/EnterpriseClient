package com.example.enterpriseclient.bottomNavigationView.user

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.*
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.R
import com.example.enterpriseclient.model.ProductProfilePojo
import com.example.enterpriseclient.requestServer.RequestProduct


class UserFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel

    companion object {
        fun newInstance(): UserFragment = UserFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_user, container, false)

        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        var productsList=arrayListOf<ProductProfilePojo>()

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewProfile)

        val layoutManagerProducts = GridLayoutManager(root.context, 1)
        recyclerView.setLayoutManager(layoutManagerProducts)

        var number = usersViewModel.getUserId(1)

        if(number.equals("")){
            number="1"
        }


        RequestProduct.selectAllProductsForCustomer(root.context, productsList, recyclerView, number)


        var profilePencil = root.findViewById<TextView>(R.id.profile_pencil)
        var textName = root.findViewById<TextView>(R.id.textName)
        var textEmail = root.findViewById<TextView>(R.id.textEmail)
        var textRegister = root.findViewById<TextView>(R.id.textRegister)

        var list = usersViewModel.getUser(1)

        if(list != null){
            if(list.get(0).name.equals("You are not logged in")){
                textEmail.setTextColor(Color.RED)
                textRegister.setTextColor(Color.RED)
            } else {
                textEmail.setTextColor(Color.BLACK)
                textRegister.setTextColor(Color.BLACK)
                textRegister.visibility = View.GONE
            }
            textName.setText(list.get(0).name)
            textEmail.setText(list.get(0).email)
            textRegister.setText(list.get(0).token)
        }

        textEmail.setOnClickListener(){
            if(textEmail.text.toString().equals("Login")){
                val fragment = LoginFragment.newInstance()
                val activity = activity as MainActivity
                activity.openFragment(fragment)
            }
        }


        textRegister.setOnClickListener(){
            if(textRegister.text.toString().equals("Register")){
                val fragment = RegisterFragment.newInstance()
                val activity = activity as MainActivity
                activity.openFragment(fragment)
            }
        }


        profilePencil.setOnClickListener {
            if(!list!!.get(0).name.equals("You are not logged in")){
                val fragment = EditFragment.newInstance()
                val activity = activity as MainActivity
                activity.openFragment(fragment)
            }

        }

        return root
    }
}