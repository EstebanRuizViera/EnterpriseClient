package com.example.padwordbooking.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.padwordbooking.CartListActivity
import com.example.padwordbooking.R

import com.example.padwordbooking.cart.ShoppingCart
import com.example.padwordbooking.model.Availability
import com.example.padwordbooking.model.Distribution
import com.example.padwordbooking.model.Product

class CartAdapter(private val mActivity: Activity, private val mData: MutableList<Product>) :
    RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var option = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
        .error(R.drawable.loading_shape)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(mActivity)
        view = inflater.inflate(R.layout.recyclerview_cart, parent, false)

        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var activityCartList = mActivity as CartListActivity

        holder.cartName.text = mData[position].name
        holder.cartDate.text = mData[position].availabilities[0].dateAvailability
        holder.cartTime.text = mData[position].availabilities[0].timeAvailability
        holder.cartPrice.text = mData[position].availabilities[0].price.toString()+"€"

        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mActivity).load(mData[position].img).apply(option)
            .into(holder.thumbnail)

        holder.removeItem.setOnClickListener {
            holder.bindProduct(
                Product(mData[position].id,
                    mData[position].name,
                    mData[position].description,
                    mData[position].price,
                    mData[position].img, Distribution(0,"",123,"","",10),
                    mData[position].availabilities
                )
            )
            mData.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,mData.size)

            activityCartList.calculatePrice(mData)
            activityCartList.findViewById<TextView>(R.id.number_item).text = ""+activityCartList.resources.getString(R.string.carlist_number_item) +" "+mData.size.toString()
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cartName: TextView
        var cartDate: TextView
        var cartTime: TextView
        var cartPrice: TextView
        var thumbnail: ImageView

        var removeItem: TextView

        @SuppressLint("CheckResult")
        fun bindProduct(product: Product) {
            ShoppingCart.removeItem(product)
            Toast.makeText(
                itemView.context,
                "${product.name} delete to your cart",
                Toast.LENGTH_SHORT
            ).show()

        }

        init {
            cartName = itemView.findViewById(R.id.cart_product)
            cartDate = itemView.findViewById(R.id.cart_date)
            cartTime = itemView.findViewById(R.id.cart_time)
            cartPrice = itemView.findViewById(R.id.cart_price)
            thumbnail = itemView.findViewById(R.id.thumbnail_cart)
            removeItem = itemView.findViewById(R.id.remove_item)
        }

    }
}


