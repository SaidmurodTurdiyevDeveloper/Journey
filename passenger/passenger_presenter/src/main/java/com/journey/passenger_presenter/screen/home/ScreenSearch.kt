package com.journey.passenger_presenter.screen.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.common_utils.other.Constants
import com.journey.common_utils.other.extention.showSnackBar
import com.journey.common_utils.other.extention.showToast
import com.journey.passenger_domen.model.state.PlaceListState
import com.journey.passenger_presenter.adapter.PlaceSearchAdapter
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.databinding.ScreenPlacesWithTypeBinding
import com.journey.passenger_presenter.databinding.ScreenSearchBinding
import com.journey.passenger_presenter.model.PlaceEvent
import com.journey.passenger_presenter.utills.showErrorDialog
import com.journey.passenger_presenter.viewModel.ViewModelPlace
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScreenSearch : Fragment(R.layout.screen_search) {
    private val binding: ScreenSearchBinding by viewBinding()
    private var adapter = PlaceSearchAdapter()
    private val placeViewModel: ViewModelPlace by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewClickable()
        loadViewDetails()
        placeViewModel.onEvent(PlaceEvent.LoadAllPlace)
    }

    private fun loadViewDetails() {
        binding.etSearch.addTextChangedListener {
            lifecycleScope.launch {
                val text = binding.etSearch.text.toString().trim()
                delay(700)
                val newText = binding.etSearch.text.toString().trim()
                if (text == newText) {
                    adapter.filter(newText)
                }
            }
        }
        binding.list.adapter = adapter
        lifecycleScope.launch {
            placeViewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is ViewModelPlace.UiEvent.ShowSnackBar -> {
                        showSnackBar(event.message)
                    }

                    is ViewModelPlace.UiEvent.ShowToast -> {
                        showToast(event.message)
                    }
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            placeViewModel.places.collectLatest { state ->
                when (state) {
                    is PlaceListState.Error -> {
                        showErrorDialog(state.message)
                    }

                    is PlaceListState.Success -> {
                        adapter.submitList(state.data)
                    }
                }
            }
        }
    }

    private fun loadViewClickable() {
        binding.title.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        adapter.setItemClick { placeId ->
            findNavController().navigate(
                R.id.action_screenSearch_to_screenPlace,
                bundleOf(
                    Constants.MOVE_ID to placeId
                )
            )
        }
    }
}