package com.example.cricketapp.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cricketapp.databinding.ActivityMainBinding

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

        viewModel.getMatchDetailsAPI1()
        viewModel.getMatchDetailsAPI2()

        _adapter = MatchesListAdapter()
        binding.recyclerView.adapter = adapter


        viewModel.matchDetails.observe(this) { matchDetailsList ->
            if (!matchDetailsList.isNullOrEmpty()) {
                adapter.setData(viewModel.getMatchesListData())
            }
        }

        setContentView(binding.root)
    }

}