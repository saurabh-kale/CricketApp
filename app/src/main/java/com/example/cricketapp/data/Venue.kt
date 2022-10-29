package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String
)