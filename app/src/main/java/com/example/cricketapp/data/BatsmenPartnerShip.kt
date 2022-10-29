package com.example.cricketapp.data


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BatsmenPartnerShip(
    @SerializedName("Balls")
    val balls: String,
    @SerializedName("Batsman")
    val batsman: String,
    @SerializedName("Runs")
    val runs: String
)