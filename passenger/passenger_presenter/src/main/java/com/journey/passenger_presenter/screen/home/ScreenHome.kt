package com.journey.passenger_presenter.screen.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import com.journey.passenger_domen.model.state.PlaceListState
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.adapter.PlacesAdapter
import com.journey.passenger_presenter.databinding.ScreenHomeBinding
import com.journey.passenger_presenter.viewModel.ViewModelAdvertise
import com.journey.passenger_presenter.viewModel.ViewModelPlace
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScreenHome : Fragment(R.layout.screen_home), NavigationView.OnNavigationItemSelectedListener {

    private val binding: ScreenHomeBinding by viewBinding()
    private val placeViewModel: ViewModelPlace by viewModels()
    private val advertiseViewModel: ViewModelAdvertise by viewModels()

    private var nearRiverAdapter = PlacesAdapter()
    private var landscapeAdapter = PlacesAdapter()
    private var nearMountainAdapter = PlacesAdapter()
    private var reserveAdapter = PlacesAdapter()
    private var shrineAdapter = PlacesAdapter()
    private var historicalBuildingAdapter = PlacesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewClickable()
        loadViewDetails()
    }

    private fun loadViewDetails() {
        lifecycleScope.launchWhenStarted {
            placeViewModel.placesWithType.collectLatest { state ->
                binding.cvNearRivers.isVisible = state.nearRiver.isNotEmpty()
                binding.cvLandscape.isVisible = state.landscape.isNotEmpty()
                binding.cvHistoricalBuilding.isVisible = state.historicalBuilding.isNotEmpty()
                binding.cvReserve.isVisible = state.reserve.isNotEmpty()
                binding.cvShrine.isVisible = state.shrine.isNotEmpty()
                binding.cvNearMountain.isVisible = state.nearMountain.isNotEmpty()
                // submit adapter
                nearRiverAdapter.differ.submitList(state.nearRiver)
                landscapeAdapter.differ.submitList(state.landscape)
                historicalBuildingAdapter.differ.submitList(state.historicalBuilding)
                reserveAdapter.differ.submitList(state.reserve)
                shrineAdapter.differ.submitList(state.shrine)
                nearMountainAdapter.differ.submitList(state.nearMountain)
            }
        }
        lifecycleScope.launchWhenStarted {
            binding.vpFamousPlace.adapter=
        }
    }

    private fun loadViewClickable() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.region_andijon -> {

            }

            R.id.region_bukhara -> {
            }

            R.id.region_fergana -> {
            }

            R.id.region_jizzakh -> {
            }

            R.id.region_khorazm -> {
            }

            R.id.region_namangan -> {
            }

            R.id.region_navoi -> {
            }

            R.id.region_qashqadaro -> {
            }

            R.id.region_qorqalpoq -> {
            }

            R.id.region_samarand -> {
            }

            R.id.region_sirdaryo -> {
            }

            R.id.region_surkhandaryo -> {
            }

            R.id.region_tashkent -> {
            }
        }
        return true
    }

    private fun openPlace(id: Int) {

    }


    private fun openAdvertise(id: Int) {

    }

    private fun openLoading() {

    }

    private fun showError(message: String) {

    }
}














