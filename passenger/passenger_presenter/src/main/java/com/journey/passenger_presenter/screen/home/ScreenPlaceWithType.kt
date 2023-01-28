package com.journey.passenger_presenter.screen.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.journey.passenger_presenter.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenPlaceWithType : Fragment(R.layout.screen_places_with_type) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleInformation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private fun loadViewDetails() {

    }

    private fun loadViewClickable() {

    }

    private fun showError(message: String) {

    }

    private fun openLoading() {

    }

    private fun getBundleInformation() {

    }
}