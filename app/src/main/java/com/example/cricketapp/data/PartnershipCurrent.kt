package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data  class PartnershipCurrent(
    @SerializedName("Runs")
    val runs : String,
    @SerializedName("Balls")
    val balls : String,
    @SerializedName("Batsmen")
    val batsmenPartnerShip: BatsmenPartnerShip
)