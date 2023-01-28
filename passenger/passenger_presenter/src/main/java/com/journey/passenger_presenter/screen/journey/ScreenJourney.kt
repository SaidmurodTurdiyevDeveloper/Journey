package com.journey.passenger_presenter.screen.journey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.passenger_domen.model.JourneyData
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.adapter.JourneyAdapter
import com.journey.passenger_presenter.databinding.ScreenJourneyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenJourney : Fragment(R.layout.screen_journey) {
    private val binding: ScreenJourneyBinding by viewBinding()
    private lateinit var adapter: JourneyAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter= JourneyAdapter(resources)
        loadViewDetails()
    }


    private fun loadViewDetails() {
        binding.rv.adapter = adapter
        val list = ArrayList<JourneyData>()
        adapter.submitList(list)
    }
}