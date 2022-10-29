package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
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