package com.example.enterpriseclient.bottomNavigationView.settings

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.enterpriseclient.MainActivity
import com.example.enterpriseclient.R

class SharePreferenceDarkMode {

    internal var mySharedPref: SharedPreferences
    val activity: Activity

    constructor(activity: Activity) {
        this.activity = activity
        mySharedPref = activity.getSharedPreferences("filename", Context.MODE_PRIVATE)

    }

    fun setNightModeState(state: Boolean?) {
        val editor = mySharedPref.edit()
        editor.putBoolean("Night Mode", state!!)
        editor.commit()
    }

    fun loadNightModeState(): Boolean? {
        return mySharedPref.getBoolean("Night Mode", false)
    }



    companion object{
        @JvmStatic
        fun checkDarkMode(activity: Activity){
            var sharedPref =
                SharePreferenceDarkMode(
                    activity
                )
            if (sharedPref.loadNightModeState() == true) {
                activity.setTheme(R.style.darkTheme)
            } else
                activity.setTheme(R.style.AppTheme)
        }

        @JvmStatic
        fun restartApp(activity: Activity) {
            val i = Intent(activity, MainActivity::class.java)
            activity.startActivity(i)
            activity.finish()
        }
    }

}