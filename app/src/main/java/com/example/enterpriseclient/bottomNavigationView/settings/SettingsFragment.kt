package com.example.enterpriseclient.bottomNavigationView.settings


import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.preference.*
import com.example.enterpriseclient.MainActivity
import com.example.enterpriseclient.R
import com.example.enterpriseclient.SharePreferenceDarkMode
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var switchPreference: SwitchPreference
    private lateinit var listPreference: ListPreference
    internal lateinit var sharedpref: SharePreferenceDarkMode


    companion object {
        fun newInstance(): SettingsFragment =
            SettingsFragment()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        SharePreferenceDarkMode.checkDarkMode(activity as Activity)

        setPreferencesFromResource(R.xml.pref_main, rootKey)

        changeValueSwitch()

    }

    private fun changeValueSwitch() {


        switchPreference = findPreference("darkMode") as androidx.preference.SwitchPreference

        sharedpref = SharePreferenceDarkMode(activity as Activity)

        if (sharedpref.loadNightModeState() == true) {
            switchPreference!!.isChecked = true
        }

        switchPreference.setOnPreferenceChangeListener(object :
            Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(
                preference: Preference?,
                newValue: Any
            ): Boolean {
                if (newValue as Boolean == true) {
                    sharedpref.setNightModeState(true)
                    SharePreferenceDarkMode.restartApp(activity as Activity)
                    return true
                }
                sharedpref.setNightModeState(false)
                SharePreferenceDarkMode.restartApp(activity as Activity)
                return true
            }
        })
    }

    private fun changeLanguage() {
        listPreference = findPreference("Language") as ListPreference

        val intent = Intent(this.context, MainActivity::class.java)

        listPreference.setOnPreferenceChangeListener(object :
            Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference?, newValue: Any): Boolean {

                if (listPreference!!.getKey().equals("english")) {

                    selectLanguageEnglish(intent)
                    Log.println(Log.INFO, null, "spanish ")
                } else if (listPreference!!.getKey().equals("spanish")) {
                    selectLanguageSpanish(intent)
                    Log.println(Log.INFO, null, "english ")
                }
                Log.println(Log.INFO, null, "true ")
                return true
            }
        })

    }

    private fun selectLanguageSpanish(intent : Intent){
        val localizacion = Locale("es", "ES")

        Locale.setDefault(localizacion)
        val config = Configuration()
        config.locale = localizacion
        this.activity!!.baseContext.resources
            .updateConfiguration(
                config,
                this.activity!!.baseContext.resources.displayMetrics
            )
        startActivity(intent)
    }

    private fun selectLanguageEnglish(intent : Intent){
        val localizacion = Locale("en", "EN")

        Locale.setDefault(localizacion)
        val config = Configuration()
        config.locale = localizacion
        this.activity!!.baseContext.resources
            .updateConfiguration(
                config,
                this.activity!!.baseContext.resources.displayMetrics
            )
        startActivity(intent)
    }
}
