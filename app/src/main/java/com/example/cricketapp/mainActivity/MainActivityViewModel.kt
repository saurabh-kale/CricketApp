package com.example.cricketapp.mainActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricketapp.api.CricketAPPAPI
import com.example.cricketapp.data.MatchDetailsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class MainActivityViewModel : ViewModel() {

    private var _matchDetails = MutableLiveData<ArrayList<MatchDetailsData>>()
    val matchDetails: LiveData<ArrayList<MatchDetailsData>>
        get() = _matchDetails

    private var monthData: HashMap<Int, String> = HashMap()

    init {
        _matchDetails.value = ArrayList()
        addMonthData()
    }

    private val TAG = "MainActivityViewModel"

    private fun addMonthData() {
        monthData[1] = "Jan"
        monthData[2] = "Feb"
        monthData[3] = "March"
        monthData[4] = "April"
        monthData[5] = "May"
        monthData[6] = "June"
        monthData[7] = "July"
        monthData[8] = "Aug"
        monthData[9] = "Sep"
        monthData[10] = "Oct"
        monthData[11] = "Nov"
        monthData[12] = "Dec"
    }

    // get match details API 1
    fun getMatchDetailsAPI1() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                CricketAPPAPI.retrofitService.getMatchDetailsAPI1()
            }.apply {
                _matchDetails.value?.add(this)
                _matchDetails.value = _matchDetails.value
            }

        }
    }

    fun getMatchesListData(): ArrayList<MatchesListData> {
        val matchesListData: ArrayList<MatchesListData> = ArrayList()
        val c: Calendar = Calendar.getInstance()
        val format1 = SimpleDateFormat("MM/dd/yyyy", Locale.US)

        _matchDetails.value?.forEach { it ->
            val teamAName: String = it.teamData[it.matchDetail.team_home]?.name_full ?: ""
            val teamBName: String = it.teamData[it.matchDetail.team_away]?.name_full ?: ""

            val dt1: Date? = format1.parse(it.matchDetail.match.date)
            dt1?.let { it1 -> c.time = it1 }
            val month: Int = c.get(Calendar.MONTH) + 1
            val dateOfMonth: Int = c.get(Calendar.DAY_OF_MONTH)

            val dateTime: String =
                monthData[month] + " " + dateOfMonth + ", " + it.matchDetail.match.time
            val venue: String = it.matchDetail.venue.name
            matchesListData.add(
                MatchesListData(
                    teamA = teamAName,
                    teamB = teamBName,
                    dateTime = dateTime,
                    venue = venue
                )
            )
        }
        return matchesListData
    }

    // get match details API 2
    fun getMatchDetailsAPI2() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                CricketAPPAPI.retrofitService.getMatchDetailsAPI2()
            }.apply {
                _matchDetails.value?.add(this)
                _matchDetails.value = _matchDetails.value
            }

        }
    }
}