package com.journey.passenger_presenter.screen.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.journey.common_utils.other.Constants
import com.journey.common_utils.other.extention.showSnackBar
import com.journey.common_utils.other.extention.showToast
import com.journey.passenger_domen.model.ImagesAdvertise
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.databinding.ScreenPlaceBinding
import com.journey.passenger_presenter.model.JourneyEvent
import com.journey.passenger_presenter.model.PlaceEvent
import com.journey.passenger_presenter.viewModel.ViewModelJourney
import com.journey.passenger_presenter.viewModel.ViewModelPlace
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class ScreenPlace : Fragment(R.layout.screen_place) {
    private var placeId: Int? = null
    private val placeViewModel: ViewModelPlace by viewModels()
    private val journeyViewModel: ViewModelJourney by viewModels()
    private val binding: ScreenPlaceBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleInformation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewDetails()
        loadViewClickable()
        placeId?.let {
            placeViewModel.onEvent(PlaceEvent.GetPlace(it))
        }
    }

    private fun loadViewDetails() {
        lifecycleScope.launchWhenStarted {
            placeViewModel.place.collectLatest { place ->
                place?.apply {
                    binding.name.text = name
                    binding.description.text = info
                    val list = ArrayList<CarouselItem>()
                    imageList.forEach { image ->
                        list.add(
                            CarouselItem(
                                imageDrawable = image
                            )
                        )
                    }
                    binding.imageCarousel.setData(list)
                    Glide.with(this@ScreenPlace).load(mapImage).into(binding.ivMap)
                }
            }
        }
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

    }


    private fun loadViewClickable() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.addBtn.setOnClickListener {
            placeId?.let {
                journeyViewModel.onEvent(JourneyEvent.AddJourney(it))
            }
        }
    }


    private fun getBundleInformation() {
        placeId = requireArguments().getInt(Constants.MOVE_ID)
    }
}