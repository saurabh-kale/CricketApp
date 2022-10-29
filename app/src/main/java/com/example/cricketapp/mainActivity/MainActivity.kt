package com.example.cricketapp.mainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cricketapp.data.MatchDetailsData
import com.example.cricketapp.databinding.ActivityMainBinding
import com.example.cricketapp.teamStats.TeamStats
import com.google.gson.Gson
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    // binding declaration
    private lateinit var binding: ActivityMainBinding

    // viewModel declaration
    private lateinit var viewModel: MainActivityViewModel

    private val TAG = "MainActivity"

    private var _adapter: MatchesListAdapter? = null
    private val adapter get() = _adapter!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // bind layout file to MainActivity
        binding = ActivityMainBinding.inflate(layoutInflater)
        // set binding life cycle owner to MainActivity
        binding.lifecycleOwner = this

        // initialize view model
        viewModel = MainActivityViewModel()

        // get match details API
        viewModel.getMatchDetailsAPI1()
        viewModel.getMatchDetailsAPI2()

        // initialize adapter.
        _adapter = MatchesListAdapter(object : MatchesListAdapter.NavigateInterface {
            override fun navigate(position: Int) {
                val intent = Intent(this@MainActivity, TeamStats::class.java)
                // get selected match details data.
                val data = viewModel.matchDetails.value?.get(position)

                // pass selected match details data to next activity via bundle.
                val bundle = Bundle()
                bundle.putString("data", Gson().toJson(data))
                intent.putExtras(bundle)
                // start Team statistics activity
                startActivity(intent)
            }

        })
        binding.recyclerView.adapter = adapter


        viewModel.matchDetails.observe(this) { matchDetailsList ->
            if (!matchDetailsList.isNullOrEmpty()) {
                // get matches list data to set in Adapter from the matchDetails data
                adapter.setData(viewModel.getMatchesListData())
            }
        }

        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _adapter = null
    }
}