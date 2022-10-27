package com.example.cricketapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricketapp.api.APIService
import com.example.cricketapp.api.CricketAPPAPI
import com.example.cricketapp.data.MatchDetailsData
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {

    private var _matchDetails = MutableLiveData<ArrayList<MatchDetailsData>>()
    val matchDetails: LiveData<ArrayList<MatchDetailsData>>
        get() = _matchDetails

    init {
        _matchDetails.value = ArrayList()
    }

    private val TAG = "MainActivityViewModel"

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