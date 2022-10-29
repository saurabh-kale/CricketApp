package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Officials(
    @SerializedName("Umpires")
    val umpires: String,
    @SerializedName("Referee")
    val referee: String
)