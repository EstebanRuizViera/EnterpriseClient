package com.example.padwordbooking.model

import java.io.Serializable


data class Availability(
    var id: Int,
    var dateAvailability: String,
    var timeAvailability: String,
    var price: Double,
    var quota: Double,
    var id_product: Int
) :Serializable