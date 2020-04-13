package com.example.padwordbooking.model

import java.io.Serializable


data class Distribution(
    var id: Int,
    var unit: String,
    var duration: Int,
    var time_start: String,
    var time_finish: String,
    var block: Int
) : Serializable