package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TeamData(
    @SerializedName("Name_Full")
    val name_full: String,
    @SerializedName("Name_Short")
    val name_short: String,
    @SerializedName("Players")
    val players: HashMap<String, PlayerData>
)