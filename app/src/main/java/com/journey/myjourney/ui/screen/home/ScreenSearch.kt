package com.journey.myjourney.ui.screen.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.myjourney.R
import com.journey.myjourney.databinding.ScreenSearchBinding
import com.journey.myjourney.other.Constants
import com.journey.myjourney.other.StateViewModel
import com.journey.myjourney.ui.adapter.PlaceSearchAdapter
import com.journey.myjourney.ui.viewModel.PlaceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScreenSearch : Fragment(R.layout.screen_search) {
    private val viewModel: PlaceViewModel by viewModels()
    private val binding: ScreenSearchBinding by viewBinding()
    private var adapter = PlaceSearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDataScreenSearch()
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
}