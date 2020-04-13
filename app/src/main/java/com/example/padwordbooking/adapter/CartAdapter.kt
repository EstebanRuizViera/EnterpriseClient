package com.example.padwordbooking.adapter

import android.annotation.SuppressLint
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
import com.example.padwordbooking.R

import com.example.padwordbooking.cart.ShoppingCart
import com.example.padwordbooking.model.Availability
import com.example.padwordbooking.model.CartItem
import com.example.padwordbooking.model.Distribution
import com.example.padwordbooking.model.Product

class CartAdapter(private val mContext: Context, private val mData: MutableList<CartItem>) :
    RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var option = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
        .error(R.drawable.loading_shape)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(mContext)
        view = inflater.inflate(R.layout.recyclerview_cart, parent, false)

        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cartName.text = mData[position].product.name
        holder.cartDate.text = mData[position].product.availabilities[0].dateAvailability
        holder.cartTime.text = mData[position].product.availabilities[0].timeAvailability
        holder.cartPrice.text = mData[position].product.availabilities[0].price.toString()+"€"

        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData[position].product.img).apply(option)
            .into(holder.thumbnail)

        holder.removeItem.setOnClickListener {
            holder.bindProduct(
                Product(
                    mData[position].product.id,
                    mData[position].product.name,
                    mData[position].product.description,
                    mData[position].product.price,
                    mData[position].product.img, Distribution(0,"",123,"","",10),
                    arrayListOf<Availability>()
                )
            )
            mData.removeAt(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,mData.size);
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

        var removeItem: ImageView

        @SuppressLint("CheckResult")
        fun bindProduct(product: Product) {
            val item = CartItem(product)
            ShoppingCart.removeItem(item, itemView.context)
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


