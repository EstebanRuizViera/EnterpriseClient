package com.example.enterpriseclient

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class SharePreferenceDarkMode {

    internal var mySharedPref: SharedPreferences
    lateinit var activity: Activity

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

    fun restartApp() {
        val i = Intent(activity, MainActivity::class.java)
        activity.startActivity(i)
        activity.finish()
    }
}