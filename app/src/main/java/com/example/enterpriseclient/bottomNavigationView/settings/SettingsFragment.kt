package com.example.enterpriseclient.bottomNavigationView.settings


import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.*
import com.example.enterpriseclient.Constants
import com.example.enterpriseclient.MainActivity
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestUser
import java.util.*
import com.example.enterpriseclient.R.xml.pref_main

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var switchPreference: SwitchPreference
    private lateinit var listPreference: ListPreference
    internal lateinit var sharedpref: SharePreferenceDarkMode
    private lateinit var logout: Preference
    private lateinit var deleteAccount : Preference
    private lateinit var frequently_asked_questions : Preference

    private lateinit var usersViewModel: UsersViewModel


    companion object {
        fun newInstance(): SettingsFragment =
            SettingsFragment()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        SharePreferenceDarkMode.checkDarkMode(activity as Activity)
        setPreferencesFromResource(pref_main, rootKey)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        changeValueSwitch()
        changeLanguage()
        logout()
        delete()
        helpHtml()
    }

    private fun changeValueSwitch() {


        switchPreference = findPreference("darkMode") as SwitchPreference

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


                if (listPreference!!.value.equals("English")) {
                    selectLanguageSpanish(intent)

                } else if (listPreference!!.value.equals("Spanish")) {
                    selectLanguageEnglish(intent)

                }
                return true
            }
        })

    }
    private fun logout() {
        logout = findPreference("log_out")!!

        logout.setOnPreferenceClickListener(object: Preference.OnPreferenceClickListener {
            override fun onPreferenceClick(preference: Preference?): Boolean {

                val builder = AlertDialog.Builder(context!!)
                builder.setTitle("Log out")
                builder.setMessage("Do you want to logout?")
                builder.setPositiveButton("yes", {
                    dialog: DialogInterface?, which: Int ->
                    RequestUser.logout(context!!,usersViewModel)
                })

                builder.setNegativeButton("no", {dialog: DialogInterface?, which: Int -> })
                builder.show()

                return true
            }
        })
    }

    private fun delete() {
        deleteAccount = findPreference("delete_account")!!

        deleteAccount.setOnPreferenceClickListener(object: Preference.OnPreferenceClickListener {
            override fun onPreferenceClick(preference: Preference?): Boolean {

                val builder = AlertDialog.Builder(context!!)
                builder.setTitle("Delete account")
                builder.setMessage("Do you want to delete your account?")

                builder.setPositiveButton("yes", {
                        dialog: DialogInterface?, which: Int ->
                    RequestUser.deleteUser(context!!,usersViewModel)
                })

                builder.setNegativeButton("no", {dialog: DialogInterface?, which: Int -> })
                builder.show()


                return true
            }
        })
    }

    private fun selectLanguageSpanish(intent : Intent){
        val locale = Locale("es", "ES")
        Locale.setDefault(locale)
        val resources = getResources()
        val configuration = resources.getConfiguration()
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.getDisplayMetrics())
        startActivity(intent)
    }

    private fun selectLanguageEnglish(intent : Intent){

        val locale = Locale("en", "EN")
        Locale.setDefault(locale)
        val resources = getResources()
        val configuration = resources.getConfiguration()
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.getDisplayMetrics())

        startActivity(intent)
    }


    private fun helpHtml(){
        frequently_asked_questions = findPreference("frequently_asked_questions")!!

        frequently_asked_questions.setOnPreferenceClickListener(object: Preference.OnPreferenceClickListener {
            override fun onPreferenceClick(preference: Preference?): Boolean {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_SERVER+"/paginahelp/index.html"))
                context!!.startActivity(intent)
                return true
            }
        })

    }

}
