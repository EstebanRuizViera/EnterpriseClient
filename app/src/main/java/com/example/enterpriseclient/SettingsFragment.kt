package com.example.enterpriseclient


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceFragmentCompat
import com.example.flight.tabMenu.user.UserFragment

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_main, rootKey)
    }

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }


}
