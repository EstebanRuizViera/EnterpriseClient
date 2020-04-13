package com.example.padwordbooking

import android.app.Application
import android.util.Log
import androidx.preference.PreferenceManager
import com.example.padwordbooking.myDataBase.model.User
import com.example.padwordbooking.myDataBase.viewModel.UsersViewModel
import io.paperdb.Paper
import java.util.*

class App : Application() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate() {
        super.onCreate()

        Paper.init(this)

        usersViewModel = UsersViewModel(this)

        //Registro que contendrá la información del usuario que este logeado
        setFirthUserLocalDatabase()

        setLanguage()

    }

    private fun setFirthUserLocalDatabase() {
        if (usersViewModel.getUserIdLocal(1) == 0) {
            usersViewModel.saveUser(User(1, "", "You are not logged in", "Login", "Register"))
            Log.println(Log.INFO, null, "Guardado ")
        } else {
            Log.println(Log.INFO, null, "No guardado ")
        }
    }

    fun setLanguage(){
        var change = ""
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val language = sharedPreferences.getString("language", "bak")
        if (language == "Spanish") {
            change="es"
        } else if (language=="English" ) {
            change = "en"
        }else {
            change =""
        }
        //set any locale you want here
        BaseActivity.dLocale = Locale(change)
    }

}