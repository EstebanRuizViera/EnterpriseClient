package com.example.enterpriseclient


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestUser
import kotlinx.android.synthetic.main.activity_edit_profile.*


class EditProfileActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        setSupportActionBar(toolbar)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        editBtn.setOnClickListener(){
            RequestUser.updateUser(this,usersViewModel,editName,editEmail)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
