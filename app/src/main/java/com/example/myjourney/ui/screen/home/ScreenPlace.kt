package com.example.myjourney.ui.screen.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myjourney.R
import com.example.myjourney.databinding.ScreenPlaceBinding
import com.example.myjourney.other.Constants
import com.example.myjourney.other.StateViewModel
import com.example.myjourney.other.extention.showToast
import com.example.myjourney.ui.viewModel.PlaceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class ScreenPlace : Fragment(R.layout.screen_place) {
    private val viewModel: PlaceViewModel by viewModels()
    private val binding: ScreenPlaceBinding by viewBinding()
    private var journeyId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleInformation()
        viewModel.loadDataPlaceScreen(journeyId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewClickable()
        loadViewDetails()
    }

    private fun loadViewDetails() {
        lifecycleScope.launchWhenStarted {
            viewModel.statePlaceItem.collectLatest { state ->
                when (state) {
                    is StateViewModel.Loading -> {
                        openLoading()
                    }
                    is StateViewModel.Error -> {
                        showError(state.message)
                    }
                    is StateViewModel.Success -> {
                        binding.imageCarousel.addData(CarouselItem(state.data.picture))
                        state.data.imageList.forEach { data ->
                            binding.imageCarousel.addData(CarouselItem(data))
                        }
                        binding.name.text = state.data.name
                        binding.description.text = state.data.info
                        binding.ivMap.setImageResource(state.data.mapImage)
                        if (!state.data.isCanAdd) {
                            binding.addBtn.setOnClickListener { }
                            binding.addBtn.background.setTint(Color.parseColor("#7B817F"))
                        }
                    }
                }
            }
        }
    }

    private fun openLoading() {

    }

    private fun showError(message: String) {
        showToast(message)
    }

    private fun loadViewClickable() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.addBtn.setOnClickListener {
            viewModel.addJourney(journeyId)
        }
    }


    private fun getBundleInformation() {
        journeyId = arguments?.getInt(Constants.MOVE_ID, -1) ?: -1
    }
}