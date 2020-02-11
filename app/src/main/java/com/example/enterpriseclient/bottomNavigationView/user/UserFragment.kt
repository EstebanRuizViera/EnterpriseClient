package com.example.enterpriseclient.bottomNavigationView.user

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.enterpriseclient.R
import com.example.enterpriseclient.SharePreferenceDarkMode

class UserFragment : Fragment() {

    internal lateinit var sharedpref: SharePreferenceDarkMode

    companion object {
        fun newInstance(): UserFragment = UserFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_user, container, false)

        sharedpref = SharePreferenceDarkMode(this.activity as Activity)
        if (sharedpref.loadNightModeState() == true) {
            root.context.setTheme(R.style.darkTheme)
        } else
            root.context.setTheme(R.style.AppTheme)

        return root
    }
}