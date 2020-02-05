package com.example.enterpriseclient.bottomNavigationView.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.enterpriseclient.R

class UserFragment : Fragment() {

    companion object {
        fun newInstance(): UserFragment = UserFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_user, container, false)

        return root
    }
}