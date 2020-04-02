package com.example.enterpriseclient.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.enterpriseclient.R
import com.example.enterpriseclient.model.ProductProfile

class UserAdapter (private val mContext: Context, private val mData: List<ProductProfile>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private var option= RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
        .error(R.drawable.loading_shape)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(mContext)
        view = inflater.inflate(R.layout.recyclerview_profile, parent, false)

        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.profileProduct.text = mData[position].name
        holder.profileDate.text = mData[position].date
        holder.profileStatus.text = mData[position].status
        holder.profilePrice.text = mData[position].price


        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData[position].img).apply(option)
            .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileProduct: TextView
        var profileDate: TextView
        var profileStatus: TextView
        var profilePrice: TextView
        var thumbnail: ImageView


        init {
            profileProduct = itemView.findViewById(R.id.profile_product)
            profileDate = itemView.findViewById(R.id.profile_date)
            profileStatus = itemView.findViewById(R.id.profile_status)
            profilePrice = itemView.findViewById(R.id.profile_price)
            thumbnail = itemView.findViewById(R.id.thumbnail)
        }
    }

}


