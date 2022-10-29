package com.example.cricketapp.teamStats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cricketapp.databinding.ActivityTeamStatsBinding

class TeamStats : AppCompatActivity() {
    private lateinit var binding: ActivityTeamStatsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}