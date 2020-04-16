package com.example.padwordbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.padwordbooking.R
import com.example.padwordbooking.cart.ShoppingCart
import com.example.padwordbooking.model.Availability
import com.example.padwordbooking.model.Product
import com.google.android.material.snackbar.Snackbar


class AvailabilityAdapter (private val mContext: Context, private var mData: List<Availability>, private val product: Product) :
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

        holder.timestamp.setOnClickListener{ view ->
            product.availabilities = arrayListOf(mData[position])

            ShoppingCart.addItem(product)
            //notify users
            Snackbar.make(
                view,
                "${product.name} added to your cart",
                Snackbar.LENGTH_LONG
            ).show()

        }
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


