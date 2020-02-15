package com.example.enterpriseclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.adapter.AvailabilityAdapter
import com.example.enterpriseclient.model.Availability
import com.example.enterpriseclient.requestServer.RequestProduct
import kotlinx.android.synthetic.main.activity_availability.*
import kotlinx.android.synthetic.main.activity_availability.toolbar
import kotlinx.android.synthetic.main.activity_reservation.*
import kotlinx.android.synthetic.main.fragment_user.*
import java.sql.Timestamp

class AvailabilityActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availability)

        setSupportActionBar(toolbar)

        var bundle: Bundle? = intent.extras
        val message = bundle!!.getInt("id")
        Log.println(Log.INFO, null, "LLEGO AQUI" + message )

        var availabilityList = arrayListOf<Availability>()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAvailability)

        //3º) Indico la disposición en la que se mostrarán los items en el RecyclerView (P.Ej: GridLayout de 2 columnas)
        val layoutManagerAvailability = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerAvailability)

        if(message != null) {
            RequestProduct.selectAvailabilityForProduct(this, calendar, availabilityList,recyclerView, message.toString())

        }

        RequestProduct.selectAvailabilityForProduct(this, calendar, availabilityList,recyclerView, "1"  )

//        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
//        val availabilityAdapter =
//            AvailabilityAdapter(
//                this,
//                availabilityList
//            )
//        recyclerView.setAdapter(availabilityAdapter)




    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
