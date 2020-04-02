package com.example.enterpriseclient.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.R
import com.example.enterpriseclient.model.Availability


class AvailabilityAdapter (private val mContext: Context, private var mData: List<Availability>) :
    RecyclerView.Adapter<AvailabilityAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(mContext)
        view = inflater.inflate(R.layout.recyclerview_availability, parent, false)

        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.timestamp.text = mData[position].timeAvailability
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var timestamp: Button

        init {
            timestamp = itemView.findViewById(R.id.button_timestamp)
        }


    }


}


