package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerBattingDetails(
    @SerializedName("Style")
    val style : String,
    @SerializedName("Average")
    val average : String,
    @SerializedName("Strikerate")
    val strike_rate : String,
    @SerializedName("Runs")
    val runs : String
)