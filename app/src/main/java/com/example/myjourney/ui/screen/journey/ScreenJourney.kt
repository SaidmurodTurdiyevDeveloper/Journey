package com.example.myjourney.ui.screen.journey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myjourney.R
import com.example.myjourney.databinding.ScreenJourneyBinding
import com.example.myjourney.domen.model.PlaceJourneyData
import com.example.myjourney.other.Constants
import com.example.myjourney.ui.adapter.JourneyAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenJourney : Fragment(R.layout.screen_journey) {
    private val binding: ScreenJourneyBinding by viewBinding()
    private lateinit var adapter: JourneyAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter=JourneyAdapter(resources)
        loadViewDetails()
    }


    private fun loadViewDetails() {
        binding.rv.adapter = adapter
        var list = ArrayList<PlaceJourneyData>()
        list.add(PlaceJourneyData(0, R.drawable.registon, "Registon", "", Constants.GONE))
        list.add(PlaceJourneyData(0, R.drawable.uzbekistan, "Uzbekistan", "", Constants.GONE))
        list.add(PlaceJourneyData(0, R.drawable.imom_al_buxoriy, "Al buxoriy", "", Constants.CURRENT_GO))
        list.add(PlaceJourneyData(0, R.drawable.samarqand, "Samarqand", "", Constants.GO))
        list.add(PlaceJourneyData(0, R.drawable.jizzax, "Jizzax", "", Constants.GO))
        adapter.submitList(list)
    }
}