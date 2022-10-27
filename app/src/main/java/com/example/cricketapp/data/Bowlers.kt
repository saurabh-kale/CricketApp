package com.example.cricketapp.data


import com.google.gson.annotations.SerializedName

data class Bowlers(
    @SerializedName("Bowler")
    val bowler: String,
    @SerializedName("Dots")
    val dots: String,
    @SerializedName("Economyrate")
    val economy_rate: String,
    @SerializedName("Maidens")
    val maidens: String,
    @SerializedName("Noballs")
    val no_balls: String,
    @SerializedName("Overs")
    val overs: String,
    @SerializedName("Runs")
    val runs: String,
    @SerializedName("Wickets")
    val wickets: String,
    @SerializedName("Wides")
    val wides: String
)