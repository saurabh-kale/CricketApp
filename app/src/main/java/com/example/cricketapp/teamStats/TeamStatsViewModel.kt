package com.example.cricketapp.teamStats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cricketapp.data.MatchDetailsData
import com.example.cricketapp.data.PlayerData

class TeamStatsViewModel : ViewModel() {

    private var _matchDetailsData = MutableLiveData<MatchDetailsData>()
    val matchDetailsData: LiveData<MatchDetailsData>
        get() = _matchDetailsData

    private var _teamAPlayersData = MutableLiveData<ArrayList<PlayerData>>()
    val teamAPlayersData: LiveData<ArrayList<PlayerData>>
        get() = _teamAPlayersData

    private var _teamBPlayersData = MutableLiveData<ArrayList<PlayerData>>()
    val teamBPlayersData: LiveData<ArrayList<PlayerData>>
        get() = _teamBPlayersData


    init {
        _teamAPlayersData.value = ArrayList()
        _teamBPlayersData.value = ArrayList()
    }

    fun setMatchDetailsData(data: MatchDetailsData) {
        _matchDetailsData.postValue(data)
    }

    fun setTeamAPlayersData() {
        val playersList: ArrayList<PlayerData> = ArrayList()
        _matchDetailsData.value?.matchDetail?.let {
            _matchDetailsData.value?.teamData?.get(it.team_home)?.players?.values?.forEach { it ->
                playersList.add(
                    PlayerData(
                        position = it.position,
                        name_full = it.name_full,
                        isKeeper = it.isKeeper,
                        isCaptain = it.isCaptain,
                        battingDetails = it.battingDetails,
                        playerBowlingDetails = it.playerBowlingDetails
                    )
                )
            }
        }
        _teamAPlayersData.value = ArrayList()
        _teamAPlayersData.postValue(playersList)
    }

    fun setTeamBPlayersData() {
        val playersList: ArrayList<PlayerData> = ArrayList()
        _matchDetailsData.value?.matchDetail?.let {
            _matchDetailsData.value?.teamData?.get(it.team_away)?.players?.values?.forEach { it ->
                playersList.add(
                    PlayerData(
                        position = it.position,
                        name_full = it.name_full,
                        isKeeper = it.isKeeper,
                        isCaptain = it.isCaptain,
                        battingDetails = it.battingDetails,
                        playerBowlingDetails = it.playerBowlingDetails
                    )
                )
            }
        }
        _teamBPlayersData.value = ArrayList()
        _teamBPlayersData.postValue(playersList)
    }


}