package com.example.enterpriseclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.enterpriseclient.tabMenu.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = SectionsPagerAdapter(supportFragmentManager)
        view_pager.adapter = fragmentAdapter

        tabs.setupWithViewPager(view_pager)
    }
}
