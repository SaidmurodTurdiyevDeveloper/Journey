package com.journey.passenger_presenter.screen.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import com.journey.passenger_domen.model.state.PlaceListState
import com.journey.passenger_presenter.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewClickable()
        loadViewDetails()
    }

    private fun loadViewDetails() {
        lifecycleScope.launch {
            placeViewModel.places.collectLatest {state->
                when(state){
                    is PlaceListState.Error -> {

                    }
                    is PlaceListState.Success -> {

                    }
                }
            }
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














