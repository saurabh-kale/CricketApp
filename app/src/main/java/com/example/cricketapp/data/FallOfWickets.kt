package com.example.cricketapp.data


import com.google.gson.annotations.SerializedName

data class FallOfWickets(
    @SerializedName("Batsman")
    val batsman: String,
    @SerializedName("Overs")
    val overs: String,
    @SerializedName("Score")
    val score: String
)