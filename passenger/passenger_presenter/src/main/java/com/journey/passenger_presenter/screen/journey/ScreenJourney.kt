package com.journey.passenger_presenter.screen.journey

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.passenger_domen.model.JourneyData
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.adapter.JourneyAdapter
import com.journey.passenger_presenter.databinding.ScreenJourneyBinding
import com.journey.passenger_presenter.menu.JourneyMoreMenu
import com.journey.passenger_presenter.model.JourneyEvent
import com.journey.passenger_presenter.viewModel.ViewModelJourney
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ScreenJourney : Fragment(R.layout.screen_journey) {
    private val binding: ScreenJourneyBinding by viewBinding()
    private lateinit var adapter: JourneyAdapter
    private val journeyViewModel: ViewModelJourney by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = JourneyAdapter(resources)
        loadViewDetails()
        loadViewClickable()
    }


    private fun loadViewDetails() {
        binding.rv.adapter = adapter
        val list = ArrayList<JourneyData>()
        adapter.submitList(list)
        lifecycleScope.launchWhenStarted {
            journeyViewModel.journeyList.collectLatest { list ->
                adapter.submitList(list)
            }
        }

    }

    private fun loadViewClickable() {
        binding.btnMore.setOnClickListener {
            JourneyMoreMenu()
                .clearJourney {
                    journeyViewModel.onEvent(JourneyEvent.ClearJourney)
                }.openMap {
                    binding.rv.isVisible = false
                    binding.webView.isVisible = true
                    binding.webView.loadUrl("")
                }.show()
        }
    }
}