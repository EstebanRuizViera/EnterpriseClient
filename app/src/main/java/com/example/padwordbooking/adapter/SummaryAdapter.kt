package com.example.padwordbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.padwordbooking.R
import com.example.padwordbooking.model.Product

class SummaryAdapter (private val mContext: Context, private var mData: List<Product>) :
    RecyclerView.Adapter<SummaryAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(mContext)
        view = inflater.inflate(R.layout.recyclerview_summary, parent, false)

        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameProduct.text = mData[position].name
        holder.priceProduct.text = ""+mContext.getString(R.string.recycler_summary_price)+" "+mData[position].availabilities[0].price.toString()+"€"
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameProduct: TextView
        var priceProduct: TextView

        init {
            nameProduct = itemView.findViewById(R.id.reyclerview_summary_name_product)
            priceProduct = itemView.findViewById(R.id.reyclerview_summary_price_product)
        }

    }


}