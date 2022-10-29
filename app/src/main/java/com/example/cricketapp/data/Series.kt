package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Series(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Tour")
    val tour: String,
    @SerializedName("Tour_Name")
    val tour_name: String

)