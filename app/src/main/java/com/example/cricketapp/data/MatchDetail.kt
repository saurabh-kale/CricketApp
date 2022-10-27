package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName

data class MatchDetail(
    @SerializedName("Team_Home")
    val team_home: String,
    @SerializedName("Team_Away")
    val team_away: String,
    @SerializedName("Match")
    val match: Match,
    @SerializedName("Series")
    val series: Series,
    @SerializedName("Venue")
    val venue: Venue,
    @SerializedName("Officials")
    val officials: Officials,
    @SerializedName("Weather")
    val weather: String,
    @SerializedName("Tosswonby")
    val tosswonby: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Status_Id")
    val status_Id: String,
    @SerializedName("Player_Match")
    val player_match: String,
    @SerializedName("Result")
    val result: String,
    @SerializedName("Winningteam")
    val winning_team: String,
    @SerializedName("Winmargin")
    val win_margin: String,
    @SerializedName("Equation")
    val equation: String
)