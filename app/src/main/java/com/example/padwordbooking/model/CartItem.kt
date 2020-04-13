package com.example.padwordbooking.model


data class CartItem(
    var product: Product,
    var quantity: Int = 0
)