package com.example.cricketapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Innings(
    @SerializedName("Number")
    val number: String,
    @SerializedName("Battingteam")
    val batting_team: String,
    @SerializedName("Total")
    val total: String,
    @SerializedName("Wickets")
    val wickets: String,
    @SerializedName("Overs")
    val overs: String,
    @SerializedName("Runrate")
    val run_rate: String,
    @SerializedName("Byes")
    val byes: String,
    @SerializedName("Legbyes")
    val leg_byes: String,
    @SerializedName("Wides")
    val wides: String,
    @SerializedName("Noballs")
    val no_balls: String,
    @SerializedName("Penalty")
    val penalty: String,
    @SerializedName("AllottedOvers")
    val allotted_overs: String,
    @SerializedName("Batsmen")
    val batsmen: ArrayList<Batsmen>,
    @SerializedName("Partnership_current")
    val partnershipCurrent: PartnershipCurrent,
    @SerializedName("Bowlers")
    val bowlers: ArrayList<Bowlers>,
    @SerializedName("FallofWickets")
    val fallOfWickets: ArrayList<FallOfWickets>,
    @SerializedName("PowerPlay")
    val powerPlay: PowerPlay
)