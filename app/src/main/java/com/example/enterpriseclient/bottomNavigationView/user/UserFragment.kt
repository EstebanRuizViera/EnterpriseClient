package com.example.enterpriseclient.bottomNavigationView.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.enterpriseclient.AvailabilityActivity
import com.example.enterpriseclient.EditProfileActivity
import com.example.enterpriseclient.R
import com.example.enterpriseclient.ReservationActivity
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode



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


        var profilePencil = root.findViewById<TextView>(R.id.profile_pencil)


        profilePencil.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }

        return root


    }
}