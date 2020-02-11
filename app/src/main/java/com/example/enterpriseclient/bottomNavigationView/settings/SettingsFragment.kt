package com.example.enterpriseclient.bottomNavigationView.settings


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.example.enterpriseclient.R


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var switchPreference :SwitchPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_main, rootKey)

        var switchPreference = findPreference("darkMode") as androidx.preference.SwitchPreference

        switchPreference.setOnPreferenceChangeListener(object : Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(
                preference: Preference?,
                newValue: Any
            ): Boolean {
                if (newValue as Boolean == true) {
                    Log.println(Log.INFO, null, "true" )
                    return true
                }
                Log.println(Log.INFO, null, "false" )
                return true
            }
        })
    }

    companion object {
        fun newInstance(): SettingsFragment =
            SettingsFragment()
    }

}
