package com.example.enterpriseclient

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.example.enterpriseclient.myDataBase.model.User
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.mySynchronized.SynchronizedLocalDatabase
import com.example.enterpriseclient.requestServer.RequestReport
import java.util.*

class App : Application() {


    private lateinit var synchronizedLocalDatabase: SynchronizedLocalDatabase
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate() {
        super.onCreate()

        usersViewModel = UsersViewModel(this)

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

        //Registro que contendrá la información del usuario que este logeado
        setFirthUserLocalDatabase()
        syncronized()

        //set any locale you want here
        BaseActivity.dLocale = Locale(change)

    }

    private fun syncronized() {
        synchronizedLocalDatabase = SynchronizedLocalDatabase(this.applicationContext,this)
        synchronizedLocalDatabase.syncronizedProduct()

    }

    private fun setFirthUserLocalDatabase() {
        if (usersViewModel.getUserIdLocal(1) == 0) {
            usersViewModel.saveUser(User(1, "", "You are not logged in", "Login", "Register"))
            Log.println(Log.INFO, null, "Guardado ")
        } else {
            Log.println(Log.INFO, null, "No guardado ")
        }
    }
}