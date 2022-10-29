package com.example.cricketapp.teamStats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.R
import com.example.cricketapp.data.PlayerData
import com.example.cricketapp.databinding.TeamPlayersLayout1Binding

class TeamAPlayersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var teamList: ArrayList<PlayerData> = ArrayList()
    var teamAName: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TeamAPlayersAdapterViewHolder.from(parent)
    }

    class TeamAPlayersAdapterViewHolder(private val binding: TeamPlayersLayout1Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PlayerData, position: Int, teamAName: String) {
            binding.playerNameText.text = data.name_full
            when (teamAName.lowercase()) {
                "india" -> binding.teamLogo.setImageResource(R.drawable.india_logo)
                "pakistan" -> binding.teamLogo.setImageResource(R.drawable.pakistan_logo)
                "new zealand" -> binding.teamLogo.setImageResource(R.drawable.new_zealand_logo)
                "south africa" -> binding.teamLogo.setImageResource(R.drawable.south_africa_logo)
            }
            if (data.isCaptain) {
                binding.playerCaptainCard.visibility = View.VISIBLE
            } else {
                binding.playerCaptainCard.visibility = View.GONE
            }

            if (data.isKeeper) {
                binding.playerWcCard.visibility = View.VISIBLE
            } else {
                binding.playerWcCard.visibility = View.GONE
            }
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                return TeamAPlayersAdapter.TeamAPlayersAdapterViewHolder(
                    TeamPlayersLayout1Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = teamList[position]
        holder as TeamAPlayersAdapterViewHolder
        holder.bind(data, position, teamAName)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    fun setData(data: ArrayList<PlayerData>) {
        teamList = ArrayList()
        teamList = data
        notifyDataSetChanged()
    }

    fun setTeamName(value: String) {
        teamAName = value
    }

}