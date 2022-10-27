package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName

data class MatchDetailsData(
    @SerializedName("Matchdetail")
    val matchDetail: MatchDetail,
    @SerializedName("Nuggets")
    val nuggets: ArrayList<String>,
    @SerializedName("Innings")
    val innings: ArrayList<Innings>,
    @SerializedName("Teams")
    val teamData: HashMap<String, TeamData>,
    @SerializedName("Notes")
    val notes: HashMap<String, ArrayList<String>>
)