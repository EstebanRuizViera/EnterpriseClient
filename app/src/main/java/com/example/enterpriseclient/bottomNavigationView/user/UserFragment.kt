package com.example.enterpriseclient.bottomNavigationView.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.*
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.AvailabilityActivity
import com.example.enterpriseclient.EditProfileActivity
import com.example.enterpriseclient.R
import com.example.enterpriseclient.ReservationActivity
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.model.Product
import com.example.enterpriseclient.model.ProductProfile
import com.example.enterpriseclient.requestServer.RequestProduct
import com.example.enterpriseclient.requestServer.RequestUser


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

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }




        var productsList=arrayListOf<ProductProfile>()

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

        var list = usersViewModel.getUser(1)

        if(list != null){
            textName.setText(list.get(0).name)
            textEmail.setText(list.get(0).email)
        }

        textEmail.setOnClickListener(){
            if(textEmail.text.toString().equals("login")){
                val fragment = LoginFragment.newInstance()
                val activity = activity as MainActivity
                activity.openFragment(fragment)
            }
        }


        profilePencil.setOnClickListener {
            //if(!textEmail.text.toString().equals("login")) {
                val intent = Intent(activity, EditProfileActivity::class.java)
                startActivity(intent)
            //}
        }

        return root


    }
}