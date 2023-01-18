package com.journey.myjourney.ui.screen.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.myjourney.R
import com.journey.myjourney.data.model.TYPE
import com.journey.myjourney.databinding.ScreenPlacesWithTypeBinding
import com.journey.myjourney.other.Constants
import com.journey.myjourney.other.StateViewModel
import com.journey.myjourney.ui.adapter.PlaceSearchAdapter
import com.journey.myjourney.ui.viewModel.PlaceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ScreenPlaceWithType : Fragment(R.layout.screen_places_with_type) {
    private val viewModel: PlaceViewModel by viewModels()
    private val binding: ScreenPlacesWithTypeBinding by viewBinding()
    private var adapter = PlaceSearchAdapter()
    private var sortType: TYPE? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleInformation()
        sortType?.let { type ->
            viewModel.loadDataPlaceWithTypeScreen(type)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewClickable()
        loadViewDetails()
    }

    private fun loadViewDetails() {
        lifecycleScope.launchWhenStarted {
            viewModel.statePlaceList.collectLatest { state ->
                when (state) {
                    is StateViewModel.Loading -> {
                        openLoading()
                    }
                    is StateViewModel.Error -> {
                        showError(state.message)
                    }
                    is StateViewModel.Success -> {
                        adapter.submitList(state.data)
                    }
                }

            }
        }
        binding.list.adapter = adapter
    }

    private fun loadViewClickable() {
        binding.title.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        adapter.setItemClick { id ->
            findNavController().navigate(R.id.action_screenPlaceWithType_to_screenPlace, bundleOf(Constants.MOVE_ID to id))
        }
    }

    private fun showError(message: String) {

    }

    private fun openLoading() {

    }

    private fun getBundleInformation() {
        sortType = if (Build.VERSION_CODES.TIRAMISU > Build.VERSION.SDK_INT) requireArguments().getParcelable(Constants.MOVE_TYPE)
        else requireArguments().getParcelable(Constants.MOVE_TYPE, TYPE::class.java)
    }
}