package com.example.enterpriseclient.bottomNavigationView.settings


import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.example.enterpriseclient.R
import com.example.enterpriseclient.SharePreferenceDarkMode


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var switchPreference :SwitchPreference
    internal lateinit var sharedpref: SharePreferenceDarkMode




    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        SharePreferenceDarkMode.checkDarkMode(this.activity as Activity)

        setPreferencesFromResource(R.xml.pref_main, rootKey)

        var activity = this.activity



        var switchPreference = findPreference("darkMode") as androidx.preference.SwitchPreference

        sharedpref = SharePreferenceDarkMode(this.activity as Activity)

        if (sharedpref.loadNightModeState() == true) {
            switchPreference!!.isChecked = true
        }

        switchPreference.setOnPreferenceChangeListener(object : Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(
                preference: Preference?,
                newValue: Any
            ): Boolean {
                if (newValue as Boolean == true) {
                    Log.println(Log.INFO, null, "true" )
                    sharedpref.setNightModeState(true)
                    SharePreferenceDarkMode.restartApp(activity as Activity)
                    return true
                }
                Log.println(Log.INFO, null, "false" )
                sharedpref.setNightModeState(false)
                SharePreferenceDarkMode.restartApp(activity as Activity)
                return true
            }
        })
    }

    companion object {
        fun newInstance(): SettingsFragment =
            SettingsFragment()
    }

}
