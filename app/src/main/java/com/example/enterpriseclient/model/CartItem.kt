package com.example.enterpriseclient.model


data class CartItem(
    var product: Product,
    var quantity: Int = 0
)