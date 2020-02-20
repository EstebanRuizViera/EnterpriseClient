package com.example.enterpriseclient

import android.app.Application
import androidx.preference.PreferenceManager
import com.example.enterpriseclient.mySynchronized.SynchronizedLocalDatabase
import com.example.enterpriseclient.requestServer.RequestReport
import java.util.*

class App : Application() {


    private lateinit var synchronizedLocalDatabase: SynchronizedLocalDatabase

    override fun onCreate() {
        super.onCreate()

        RequestReport.generateReportListProduct(this)

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

        BaseActivity.dLocale = Locale(change) //set any locale you want here
        syncronizedProduct()
    }

    private fun syncronizedProduct() {
        synchronizedLocalDatabase = SynchronizedLocalDatabase(this.applicationContext,this)
        synchronizedLocalDatabase.syncronizedProduct()
    }
}