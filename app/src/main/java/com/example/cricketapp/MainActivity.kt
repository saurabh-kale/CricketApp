package com.example.cricketapp

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

        viewModel.matchDetails.observe(this) {
            if (!it.isNullOrEmpty()) {
                Log.d(TAG, "onCreate: $it")
            }
        }

        setContentView(binding.root)
    }

}