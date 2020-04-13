package com.example.enterpriseclient.model


data class Distribution(
    var id: Int,
    var unit: String,
    var duration: Int,
    var time_start: Int,
    var time_finish: Int,
    var block: Int
)