package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName

data class PlayerBowlingDetails(
    @SerializedName("Style")
    val style: String,
    @SerializedName("Average")
    val average: String,
    @SerializedName("Economyrate")
    val economy_rate: String,
    @SerializedName("Wickets")
    val wickets: String
)