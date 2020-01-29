package com.example.flight.tabMenu.myFlight;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.R

class ReservationsAdapter(students: ArrayList<Reservations>) :
    RecyclerView.Adapter<ReservationsAdapter.ViewHolder?>() {

    var reservations: ArrayList<Reservations>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.reservations_layout, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textViewName.setText(reservations[position].name)
        holder.textViewSurname.setText(reservations[position].surname)
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textViewName: TextView
        var textViewSurname: TextView

        init {
            textViewName = v.findViewById(R.id.textViewName)
            textViewSurname = v.findViewById(R.id.textViewSurname)
        }
    }

    init {
        this.reservations = students
    }
}