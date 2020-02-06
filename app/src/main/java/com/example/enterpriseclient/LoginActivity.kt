package com.example.enterpriseclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.enterpriseclient.myDataBase.model.User
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestProduct
import com.example.enterpriseclient.requestServer.RequestUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        if(usersViewModel.getUserIdLocal(1)==0){
            usersViewModel.saveUser(User(1,"",""))
            Log.println(Log.INFO,null,"Guardado ")
        }else{
            Log.println(Log.INFO,null,"No guardado ")
        }

        lg_login.setOnClickListener(){
            RequestUser.login(this,lg_email,lg_password,usersViewModel)
        }

        lg_loginText.setOnClickListener(){
            val intent = Intent(this,RegisterActivity::class.java)
             startActivity(intent)
        }
    }
}
