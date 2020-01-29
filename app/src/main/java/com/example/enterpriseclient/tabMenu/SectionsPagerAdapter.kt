package com.example.enterpriseclient.tabMenu

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.enterpriseclient.R
import com.example.flight.tabMenu.myFlight.MyFlightFragment
import com.example.flight.tabMenu.search.SearchFragment
import com.example.flight.tabMenu.user.UserFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_1,
    R.string.tab_2,
    R.string.tab_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SearchFragment()
            }
            1 -> MyFlightFragment()
            else -> {
                return UserFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "First Tab"
            1 -> "Second Tab"
            else -> {
                return "Third Tab"
            }
        }
    }
}