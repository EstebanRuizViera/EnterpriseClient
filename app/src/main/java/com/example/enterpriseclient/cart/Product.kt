package com.example.enterpriseclient.cart

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("description")
    var date: String? = null,

    @SerializedName("price")
    var price: String? = null,

    @SerializedName("img")
    var img: String? = null

)