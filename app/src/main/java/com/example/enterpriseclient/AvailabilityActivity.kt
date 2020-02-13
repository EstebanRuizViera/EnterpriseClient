package com.example.enterpriseclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.requestServer.RequestProduct
import java.sql.Time
import java.sql.Timestamp

class AvailabilityActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availability)

        var availabilityList=arrayListOf<Availability>()

        availabilityList.add(Availability(Timestamp(14)))
        availabilityList.add(Availability(Timestamp(15)))
        availabilityList.add(Availability(Timestamp(16)))
        availabilityList.add(Availability(Timestamp(17)))


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAvailability)

        //3º) Indico la disposición en la que se mostrarán los items en el RecyclerView (P.Ej: GridLayout de 2 columnas)
        val layoutManagerAvailability = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerAvailability)

        //RequestProduct.selectAllProducts(this,availabilityList,recyclerView)

        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
        val availabilityAdapter =
            AvailabilityAdapter(
                this,
                availabilityList
            )
        recyclerView.setAdapter(availabilityAdapter)


    }


}
