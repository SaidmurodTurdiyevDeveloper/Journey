package com.journey.passenger_presenter.screen.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.common_utils.other.Constants
import com.journey.common_utils.other.extention.showSnackBar
import com.journey.common_utils.other.extention.showToast
import com.journey.common_utils.other.type.Type
import com.journey.passenger_domen.model.state.PlaceListState
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.adapter.PlaceSearchAdapter
import com.journey.passenger_presenter.databinding.ScreenPlacesWithTypeBinding
import com.journey.passenger_presenter.model.PlaceEvent
import com.journey.passenger_presenter.utills.showErrorDialog
import com.journey.passenger_presenter.viewModel.ViewModelPlace
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScreenPlaceWithType : Fragment(R.layout.screen_places_with_type) {

    private var placeType: Type? = null
    private var title: String? = null
    private val placeViewModel: ViewModelPlace by viewModels()
    private val binding: ScreenPlacesWithTypeBinding by viewBinding()
    private val adapter = PlaceSearchAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleInformation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewDetails()
        loadViewClickable()
        placeType?.let {
            placeViewModel.onEvent(PlaceEvent.LoadAllPlaceWithType(it))
        }
    }

    private fun loadViewDetails() {
        title?.let {
            binding.tvTitle.text = it
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
                R.id.action_screenPlaceWithType_to_screenPlace,
                bundleOf(
                    Constants.MOVE_ID to placeId
                )
            )
        }
    }

    private fun getBundleInformation() {
        placeType = if (Build.VERSION.SDK_INT > 32) requireArguments().getParcelable(
            Constants.MOVE_TYPE,
            Type::class.java
        ) else requireArguments().getParcelable(Constants.MOVE_TYPE)
        title = requireArguments().getString(Constants.MOVE_DATA)
    }
}