package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName

data class PowerPlay(
    @SerializedName("PP1")
    val pp1: String,
    @SerializedName("PP2")
    val pp2: String
)