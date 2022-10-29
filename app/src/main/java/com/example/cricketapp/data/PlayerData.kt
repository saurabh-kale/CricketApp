package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerData(
    @SerializedName("Position")
    val position: String,
    @SerializedName("Name_Full")
    val name_full: String,
    @SerializedName("Iskeeper")
    val isKeeper: Boolean,
    @SerializedName("Iscaptain")
    val isCaptain: Boolean,
    @SerializedName("Batting")
    val battingDetails: PlayerBattingDetails,
    @SerializedName("Bowling")
    val playerBowlingDetails: PlayerBowlingDetails
)