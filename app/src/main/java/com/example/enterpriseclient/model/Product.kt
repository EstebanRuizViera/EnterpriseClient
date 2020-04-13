package com.example.enterpriseclient.model


data class Product(
    var id: Int,
    var name: String,
    var description: String,
    var price: String,
    var img: String,
    var distribution: Distribution
)