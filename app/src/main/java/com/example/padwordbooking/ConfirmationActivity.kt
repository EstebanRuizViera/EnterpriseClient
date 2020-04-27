package com.example.padwordbooking

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.padwordbooking.fragment.settings.SharePreferenceDarkMode
import kotlinx.android.synthetic.main.activity_confirmation.*

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)


        return_home.setOnClickListener{
            val intent = Intent(this, DrawerActivity::class.java)
            startActivity(intent)
        }

        print_pdf_one.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_SERVER+"/productList.pdf"))
            startActivity(intent)
        }

        print_pdf_two.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_SERVER+"/productList.pdf"))
            startActivity(intent)
        }

    }
}
