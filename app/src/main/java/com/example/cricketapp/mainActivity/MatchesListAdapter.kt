package com.example.cricketapp.mainActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.R
import com.example.cricketapp.databinding.MatchesLayoutBinding

class MatchesListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var matchesListData: ArrayList<MatchesListData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MatchesListViewHolder.from(parent)
    }

    class MatchesListViewHolder(private val binding: MatchesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MatchesListData) {
            binding.teamA.text = data.teamA
            binding.teamB.text = data.teamB
            binding.dateTimeText.text = data.dateTime
            binding.venueDetails.text = data.venue
            when (data.teamA.lowercase()) {
                "india" -> binding.teamAImage.setImageResource(R.drawable.india_logo)
                "pakistan" -> binding.teamAImage.setImageResource(R.drawable.pakistan_logo)
                "new zealand" -> binding.teamAImage.setImageResource(R.drawable.new_zealand_logo)
                "south africa" -> binding.teamAImage.setImageResource(R.drawable.south_africa_logo)
            }
            when (data.teamB.lowercase()) {
                "india" -> binding.teamBImage.setImageResource(R.drawable.india_logo)
                "pakistan" -> binding.teamBImage.setImageResource(R.drawable.pakistan_logo)
                "new zealand" -> binding.teamBImage.setImageResource(R.drawable.new_zealand_logo)
                "south africa" -> binding.teamBImage.setImageResource(R.drawable.south_africa_logo)
            }
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                return MatchesListViewHolder(
                    MatchesLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = matchesListData[position]
        holder as MatchesListViewHolder
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return matchesListData.size
    }

    fun setData(data: ArrayList<MatchesListData>) {
        matchesListData = ArrayList()
        matchesListData = data
        notifyDataSetChanged()
    }
}