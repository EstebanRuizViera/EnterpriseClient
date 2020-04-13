package com.example.enterpriseclient.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.enterpriseclient.*
import com.example.enterpriseclient.model.Product

class ProductAdapter (private val mContext: Context, private val mData: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var option= RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
        .error(R.drawable.loading_shape)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(mContext)
        view = inflater.inflate(R.layout.recyclerview_home, parent, false)
        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.Name.text = mData[position].name


        holder.linearHome.setOnClickListener{
            val intent = Intent(mContext, ReservationActivity::class.java)
            intent.putExtra("id", mData[position].id)
            intent.putExtra("product", arrayOf(
                mData[position].id.toString(),
                mData[position].name,
                mData[position].description,
                mData[position].price,
                mData[position].img
                )
            )
            mContext.startActivity(intent)
        }


        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData[position].img).apply(option)
            .into(holder.imgThumbnail)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Name: TextView
        var imgThumbnail: ImageView
        var linearHome: LinearLayout

        init {
            Name = itemView.findViewById(R.id.productName)
            imgThumbnail = itemView.findViewById(R.id.thumbnail)
            linearHome = itemView.findViewById(R.id.linearHome)

        }
    }

}



