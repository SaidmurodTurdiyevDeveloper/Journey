package com.journey.passenger_presenter.screen.service

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.adapter.ServiceAdapter
import com.journey.passenger_presenter.databinding.ScreenServiceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServiceScreen : Fragment(R.layout.screen_service) {
    private val binding: ScreenServiceBinding by viewBinding()
    private var adapter = ServiceAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewDetails()
    }

    private fun loadViewDetails() {
        binding.rv.adapter=adapter
//        var list=ArrayList<ServiceData_Full>()
//        adapter.differ.submitList(list)
    }

}