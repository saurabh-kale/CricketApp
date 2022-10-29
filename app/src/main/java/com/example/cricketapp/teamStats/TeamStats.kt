package com.example.cricketapp.teamStats

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cricketapp.R
import com.example.cricketapp.data.MatchDetailsData
import com.example.cricketapp.data.PlayerData
import com.example.cricketapp.databinding.ActivityTeamStatsBinding
import com.google.gson.Gson

class TeamStats : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityTeamStatsBinding
    lateinit var viewModel: TeamStatsViewModel
    private val TAG = "TeamStats"

    private var _teamAPlayersAdapter: TeamAPlayersAdapter? = null
    private val teamAPlayersAdapter get() = _teamAPlayersAdapter!!

    private var _teamBPlayersAdapter: TeamBPlayersAdapter? = null
    private val teamBPlayersAdapter get() = _teamBPlayersAdapter!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
// calling the action bar

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = TeamStatsViewModel()
        binding.lifecycleOwner = this

        val extras = intent.extras
        val data = Gson().fromJson(
            extras?.getString("data"),
            MatchDetailsData::class.java
        )

        // set the match details data in viewModel data variable
        viewModel.setMatchDetailsData(data)


        _teamAPlayersAdapter = TeamAPlayersAdapter(object : TeamAPlayersAdapter.ShowInformation {
            override fun showPlayerDetails(data: PlayerData) {
                showAlertDialog(data)
            }
        })
        binding.teamAPlayersRecycler.adapter = teamAPlayersAdapter

        _teamBPlayersAdapter = TeamBPlayersAdapter(object : TeamBPlayersAdapter.ShowInformation {
            override fun showPlayerDetails(data: PlayerData) {
                showAlertDialog(data)
            }
        })
        binding.teamBPlayersRecycler.adapter = teamBPlayersAdapter
        /**
         *         observe [viewModel.matchDetailsData] to update UI when data is available.
         */
        viewModel.matchDetailsData.observe(this) {
            if (it != null) {
                val teamAShortName = it.teamData[it.matchDetail.team_home]?.name_short ?: ""
                val teamBShortName = it.teamData[it.matchDetail.team_away]?.name_short ?: ""
                binding.teamAText.text = it.teamData[it.matchDetail.team_home]?.name_short ?: ""
                binding.teamBText.text = it.teamData[it.matchDetail.team_away]?.name_short ?: ""
                when (teamAShortName.lowercase()) {
                    "ind" -> binding.teamALogo.setImageResource(R.drawable.india_logo)
                    "pak" -> binding.teamALogo.setImageResource(R.drawable.pakistan_logo)
                    "nz" -> binding.teamALogo.setImageResource(R.drawable.new_zealand_logo)
                    "sa" -> binding.teamALogo.setImageResource(R.drawable.south_africa_logo)
                }
                when (teamBShortName.lowercase()) {
                    "ind" -> binding.teamBLogo.setImageResource(R.drawable.india_logo)
                    "pak" -> binding.teamBLogo.setImageResource(R.drawable.pakistan_logo)
                    "nz" -> binding.teamBLogo.setImageResource(R.drawable.new_zealand_logo)
                    "sa" -> binding.teamBLogo.setImageResource(R.drawable.south_africa_logo)
                }
                viewModel.setTeamAPlayersData()
                viewModel.setTeamBPlayersData()
            }
        }

        viewModel.teamAPlayersData.observe(this) {
            Log.d(TAG, "teamAPlayersData: ${it.size}")
//            Log.d(TAG, "teamAPlayersData: ${Gson().toJson(it)}")
            if (!it.isNullOrEmpty()) {
                teamAPlayersAdapter.setData(it)
                teamAPlayersAdapter.setTeamName(
                    viewModel.matchDetailsData.value?.teamData?.get(
                        viewModel.matchDetailsData.value?.matchDetail?.team_home
                    )?.name_full ?: ""
                )
            }
        }

        viewModel.teamBPlayersData.observe(this) {
            Log.d(TAG, "teamBPlayersData: ${it.size}")
//            Log.d(TAG, "teamBPlayersData: ${Gson().toJson(it)}")
            if (!it.isNullOrEmpty()) {
                teamBPlayersAdapter.setData(it)
                teamBPlayersAdapter.setTeamName(
                    viewModel.matchDetailsData.value?.teamData?.get(
                        viewModel.matchDetailsData.value?.matchDetail?.team_away
                    )?.name_full ?: ""
                )
            }
        }


        // set spinner onItemSelectedListener
        binding.filtersSpinner.onItemSelectedListener = this

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.filter_option,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.filtersSpinner.adapter = adapter
        }

    }

    fun showAlertDialog(data: PlayerData) {

        val factory = LayoutInflater.from(this)
        val dialogView: View = factory.inflate(R.layout.show_player_details, null)
        val dialog = AlertDialog.Builder(this).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setView(dialogView)
        dialog.setCancelable(true)

        dialogView.findViewById<TextView>(R.id.player_name_text_view).text = data.name_full

        dialogView.findViewById<TextView>(R.id.player_position).text = "Position : " + data.position

        dialogView.findViewById<TextView>(R.id.batting_style).text =
            getString(com.example.cricketapp.R.string.style, data.battingDetails.style)

        dialogView.findViewById<TextView>(R.id.batting_average).text =
            getString(com.example.cricketapp.R.string.average, data.battingDetails.average)

        dialogView.findViewById<TextView>(R.id.batting_strike_rate).text =
            getString(com.example.cricketapp.R.string.strike_rate, data.battingDetails.strike_rate)

        dialogView.findViewById<TextView>(R.id.batting_runs).text =
            getString(com.example.cricketapp.R.string.runs, data.battingDetails.runs)

        dialogView.findViewById<TextView>(R.id.bowling_style).text =
            getString(com.example.cricketapp.R.string.style, data.playerBowlingDetails.style)

        dialogView.findViewById<TextView>(R.id.bowling_average).text =
            getString(com.example.cricketapp.R.string.average, data.playerBowlingDetails.average)

        dialogView.findViewById<TextView>(R.id.bowling_economy_rate).text =
            getString(
                com.example.cricketapp.R.string.economy_rate,
                data.playerBowlingDetails.economy_rate
            )

        dialogView.findViewById<TextView>(R.id.bowling_wickets).text =
            getString(com.example.cricketapp.R.string.wickets, data.playerBowlingDetails.wickets)


        dialogView.findViewById<TextView>(R.id.close_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    // this event will enable the back
    // function to the button on press
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
        updateUI()
    }

    // update UI to show the data according to filter selected
    private fun updateUI() {
        when (binding.filtersSpinner.selectedItem) {
            "All Teams" -> {
                binding.teamALogo.visibility = View.VISIBLE
                binding.teamAText.visibility = View.VISIBLE
                binding.teamBLogo.visibility = View.VISIBLE
                binding.teamBText.visibility = View.VISIBLE
                binding.vsText.visibility = View.VISIBLE
                binding.teamSelectedLogo.visibility = View.GONE
                binding.teamSelectedText.visibility = View.GONE
                binding.teamBPlayersRecycler.visibility = View.VISIBLE

                viewModel.setTeamAPlayersData()
                viewModel.setTeamBPlayersData()
            }
            "Team A" -> {
                binding.teamALogo.visibility = View.GONE
                binding.teamAText.visibility = View.GONE
                binding.teamBLogo.visibility = View.GONE
                binding.teamBText.visibility = View.GONE
                binding.vsText.visibility = View.GONE
                binding.teamSelectedLogo.visibility = View.VISIBLE
                binding.teamSelectedText.visibility = View.VISIBLE

                val teamAShortName =
                    viewModel.matchDetailsData.value?.teamData?.get(viewModel.matchDetailsData.value?.matchDetail?.team_home)?.name_short
                        ?: ""
                when (teamAShortName.lowercase()) {
                    "ind" -> binding.teamSelectedLogo.setImageResource(R.drawable.india_logo)
                    "pak" -> binding.teamSelectedLogo.setImageResource(R.drawable.pakistan_logo)
                    "nz" -> binding.teamSelectedLogo.setImageResource(R.drawable.new_zealand_logo)
                    "sa" -> binding.teamSelectedLogo.setImageResource(R.drawable.south_africa_logo)
                }
                binding.teamSelectedText.text = teamAShortName

                viewModel.teamAPlayersData.value?.let { teamAPlayersAdapter.setData(it) }
                teamAPlayersAdapter.setTeamName(
                    viewModel.matchDetailsData.value?.teamData?.get(
                        viewModel.matchDetailsData.value?.matchDetail?.team_home
                    )?.name_full ?: ""
                )

                binding.teamBPlayersRecycler.visibility = View.GONE

            }
            "Team B" -> {
                binding.teamALogo.visibility = View.GONE
                binding.teamAText.visibility = View.GONE
                binding.teamBLogo.visibility = View.GONE
                binding.teamBText.visibility = View.GONE
                binding.vsText.visibility = View.GONE
                binding.teamSelectedLogo.visibility = View.VISIBLE
                binding.teamSelectedText.visibility = View.VISIBLE

                val teamBShortName =
                    viewModel.matchDetailsData.value?.teamData?.get(viewModel.matchDetailsData.value?.matchDetail?.team_away)?.name_short
                        ?: ""
                when (teamBShortName.lowercase()) {
                    "ind" -> binding.teamSelectedLogo.setImageResource(R.drawable.india_logo)
                    "pak" -> binding.teamSelectedLogo.setImageResource(R.drawable.pakistan_logo)
                    "nz" -> binding.teamSelectedLogo.setImageResource(R.drawable.new_zealand_logo)
                    "sa" -> binding.teamSelectedLogo.setImageResource(R.drawable.south_africa_logo)
                }
                binding.teamSelectedText.text = teamBShortName

                viewModel.teamBPlayersData.value?.let { teamAPlayersAdapter.setData(it) }
                teamAPlayersAdapter.setTeamName(
                    viewModel.matchDetailsData.value?.teamData?.get(
                        viewModel.matchDetailsData.value?.matchDetail?.team_away
                    )?.name_full ?: ""
                )

                binding.teamBPlayersRecycler.visibility = View.GONE

            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}