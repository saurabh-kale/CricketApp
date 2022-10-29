package com.example.cricketapp.mainActivity

import com.google.gson.annotations.SerializedName

data class MatchesListData(
    @SerializedName("teamA")
    val teamA : String,
    @SerializedName("teamB")
    val teamB : String,
    @SerializedName("dateTime")
    val dateTime : String,
    @SerializedName("venue")
    val venue : String
)