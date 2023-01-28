package com.journey.passenger_presenter.screen.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.journey.common_utils.other.extention.showToast
import com.journey.passenger_presenter.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenPlace : Fragment(R.layout.screen_place) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleInformation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private fun loadViewDetails() {
    }

    private fun openLoading() {

    }

    private fun showError(message: String) {
        showToast(message)
    }

    private fun loadViewClickable() {

    }


    private fun getBundleInformation() {

    }
}