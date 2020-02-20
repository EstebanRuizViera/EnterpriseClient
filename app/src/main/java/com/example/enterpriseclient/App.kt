package com.example.enterpriseclient

import android.app.Application
import androidx.preference.PreferenceManager
import java.util.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        var change = ""
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val language = sharedPreferences.getString("language", "bak")
        if (language == "Turkish") {
            change="tr"
        } else if (language=="English" ) {
            change = "en"
        }else {
            change =""
        }

        BaseActivity.dLocale = Locale(change) //set any locale you want here
    }
}