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
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.*
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel


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

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewProfile)


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