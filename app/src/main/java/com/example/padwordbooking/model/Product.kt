package com.example.padwordbooking.model

import java.io.Serializable

data class Product (
    var id: Int,
    var name: String,
    var description: String,
    var price: String,
    var img: String,
    var distribution: Distribution,
    var availabilities: ArrayList<Availability>
) : Serializable