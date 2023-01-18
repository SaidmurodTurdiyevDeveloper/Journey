package com.journey.passenger_presenter.screen.home

import android.media.Image
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.journey.common_utils.other.Constants
import com.journey.common_utils.other.RegionConstant
import com.journey.common_utils.other.extention.changeInt
import com.journey.common_utils.other.extention.showSnackBar
import com.journey.common_utils.other.extention.showToast
import com.journey.common_utils.other.type.Type
import com.journey.passenger_domen.model.PlaceData
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.adapter.AdvertiseAdapter
import com.journey.passenger_presenter.adapter.PlacesAdapter
import com.journey.passenger_presenter.databinding.LayoutRegionHeaderBinding
import com.journey.passenger_presenter.databinding.ScreenHomeBinding
import com.journey.passenger_presenter.model.AdvertiseEvent
import com.journey.passenger_presenter.model.PlaceEvent
import com.journey.passenger_presenter.model.RegionEvent
import com.journey.passenger_presenter.viewModel.ViewModelAdvertise
import com.journey.passenger_presenter.viewModel.ViewModelPlace
import com.journey.passenger_presenter.viewModel.ViewModelRegion
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import timber.log.Timber

@AndroidEntryPoint
class ScreenHome : Fragment(R.layout.screen_home), NavigationView.OnNavigationItemSelectedListener {

    private val binding: ScreenHomeBinding by viewBinding()
    private var bindingHeader: LayoutRegionHeaderBinding? = null
    private val placeViewModel: ViewModelPlace by viewModels()
    private val advertiseViewModel: ViewModelAdvertise by viewModels()
    private val regionViewModel: ViewModelRegion by viewModels()

    private var nearRiverAdapter = PlacesAdapter()
    private var landscapeAdapter = PlacesAdapter()
    private var nearMountainAdapter = PlacesAdapter()
    private var reserveAdapter = PlacesAdapter()
    private var shrineAdapter = PlacesAdapter()
    private var historicalBuildingAdapter = PlacesAdapter()

    private lateinit var imagesAdvertiseAdapter: AdvertiseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingHeader = LayoutRegionHeaderBinding.bind(binding.navViewHome.getHeaderView(0))
        Timber.d(bindingHeader?.toString())
        loadViewClickable()
        loadViewDetails()
        regionViewModel.onEvent(RegionEvent.LoadCurrentRegion)
        advertiseViewModel.onEvent(AdvertiseEvent.LoadList)
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingHeader = null
    }

    private fun loadViewDetails() {
        binding.rvLandscape.adapter = landscapeAdapter
        binding.rvHistoricalBuilding.adapter = historicalBuildingAdapter
        binding.rvReserve.adapter = reserveAdapter
        binding.rvShrine.adapter = shrineAdapter
        binding.rvNearMountain.adapter = nearMountainAdapter
        binding.rvNearRivers.adapter = nearRiverAdapter
        lifecycleScope.launchWhenStarted {
            placeViewModel.placesWithType.collectLatest { state ->
                // submit river
                val isSingleRivers = state.nearRiver.size == 1
                binding.singleItemNearRiver.isVisible = isSingleRivers
                binding.rvNearRivers.isVisible = isSingleRivers.not()
                binding.bottomNearRivers.isVisible = isSingleRivers.not()
                if (isSingleRivers) {
                    val river = state.reserve.first()
                    Glide.with(this@ScreenHome).load(river.picture)
                        .into(binding.singleItemImageNearRiver)
                    binding.singleItemNameNearRiver.text = river.name
                    binding.singleItemNearRiver.setOnClickListener {
                        findNavController().navigate(
                            R.id.action_home_screen_to_screenPlace,
                            bundleOf(Constants.MOVE_ID to river.id)
                        )
                    }
                } else {
                    nearRiverAdapter.differ.submitList(state.nearRiver)
                }
                //submit landscape
                val isSingleLandscape = state.landscape.size == 1
                binding.singleItemLandscape.isVisible = isSingleLandscape
                binding.rvLandscape.isVisible = isSingleLandscape.not()
                binding.bottomLandscape.isVisible = isSingleLandscape.not()
                if (isSingleLandscape) {
                    val landscape = state.landscape.first()
                    Glide.with(this@ScreenHome).load(landscape.picture)
                        .into(binding.singleItemImageLandscape)
                    binding.singleItemNameLandscape.text = landscape.name
                    binding.singleItemLandscape.setOnClickListener {
                        findNavController().navigate(
                            R.id.action_home_screen_to_screenPlace,
                            bundleOf(Constants.MOVE_ID to landscape.id)
                        )
                    }
                } else {
                    landscapeAdapter.differ.submitList(state.landscape)
                }
                //submit Historical building
                val isSingleHistoricalBuilding = state.historicalBuilding.size == 1
                binding.singleItemHistoricalBuilding.isVisible = isSingleHistoricalBuilding
                binding.rvHistoricalBuilding.isVisible = isSingleHistoricalBuilding.not()
                binding.bottomHistoricalBuilding.isVisible = isSingleHistoricalBuilding.not()
                if (isSingleHistoricalBuilding) {
                    val historicalBuilding = state.historicalBuilding.first()
                    Glide.with(this@ScreenHome).load(historicalBuilding.picture)
                        .into(binding.singleItemImageHistoricalBuilding)
                    binding.singleItemNameHistoricalBuilding.text = historicalBuilding.name
                    binding.singleItemHistoricalBuilding.setOnClickListener {
                        findNavController().navigate(
                            R.id.action_home_screen_to_screenPlace,
                            bundleOf(Constants.MOVE_ID to historicalBuilding.id)
                        )
                    }
                } else {
                    historicalBuildingAdapter.differ.submitList(state.historicalBuilding)
                }
                //submit Reserve
                val isSingleReserve = state.reserve.size == 1
                binding.singleItemReserve.isVisible = isSingleReserve
                binding.rvReserve.isVisible = isSingleReserve.not()
                binding.bottomReserve.isVisible = isSingleReserve.not()
                if (isSingleReserve) {
                    val reserve = state.reserve.first()
                    Glide.with(this@ScreenHome).load(reserve.picture)
                        .into(binding.singleItemImageReserve)
                    binding.singleItemNameReserve.text = reserve.name
                    binding.singleItemReserve.setOnClickListener {
                        findNavController().navigate(
                            R.id.action_home_screen_to_screenPlace,
                            bundleOf(Constants.MOVE_ID to reserve.id)
                        )
                    }
                } else {
                    reserveAdapter.differ.submitList(state.reserve)
                }
                //submit Shrine
                val isSingleShrine = state.shrine.size == 1
                binding.singleItemShrine.isVisible = isSingleShrine
                binding.rvShrine.isVisible = isSingleShrine.not()
                binding.bottomShrine.isVisible = isSingleShrine.not()
                if (isSingleShrine) {
                    val shrine = state.shrine.first()
                    Glide.with(this@ScreenHome).load(shrine.picture)
                        .into(binding.singleItemImageShrine)
                    binding.singleItemNameShrine.text = shrine.name
                    binding.singleItemShrine.setOnClickListener {
                        findNavController().navigate(
                            R.id.action_home_screen_to_screenPlace,
                            bundleOf(Constants.MOVE_ID to shrine.id)
                        )
                    }
                } else {
                    shrineAdapter.differ.submitList(state.shrine)
                }
                //submit Near Mountain
                val isSingleNearMountain = state.nearMountain.size == 1
                binding.singleItemNearMountain.isVisible = isSingleNearMountain
                binding.rvNearMountain.isVisible = isSingleNearMountain.not()
                binding.bottomNearMountain.isVisible = isSingleNearMountain.not()
                if (isSingleNearMountain) {
                    val nearMountain = state.nearMountain.first()
                    Glide.with(this@ScreenHome).load(nearMountain.picture)
                        .into(binding.singleItemImageNearMountain)
                    binding.singleItemNameNearMountain.text = nearMountain.name
                    binding.singleItemNearMountain.setOnClickListener {
                        findNavController().navigate(
                            R.id.action_home_screen_to_screenPlace,
                            bundleOf(Constants.MOVE_ID to nearMountain.id)
                        )
                    }
                } else {
                    nearMountainAdapter.differ.submitList(state.nearMountain)
                }
                //set visible
                binding.cvNearRivers.isVisible = state.nearRiver.isNotEmpty()
                binding.cvLandscape.isVisible = state.landscape.isNotEmpty()
                binding.cvHistoricalBuilding.isVisible = state.historicalBuilding.isNotEmpty()
                binding.cvReserve.isVisible = state.reserve.isNotEmpty()
                binding.cvShrine.isVisible = state.shrine.isNotEmpty()
                binding.cvNearMountain.isVisible = state.nearMountain.isNotEmpty()
            }
        }
        lifecycleScope.launchWhenStarted {
            advertiseViewModel.imagesAdvertiseList.collectLatest { state ->
                imagesAdvertiseAdapter = AdvertiseAdapter(requireActivity(), state) { advertiseId ->
                    showToast(advertiseId.toString())
                }
                binding.vpFamousPlace.adapter = imagesAdvertiseAdapter
                binding.dotsIndicatorFamousPlace.attachTo(binding.vpFamousPlace)
                binding.layoutFamousPlace.isVisible = state.isNotEmpty()
            }
        }
        lifecycleScope.launchWhenStarted {
            advertiseViewModel.imageAdvertiseList.collectLatest { state ->
                val list = ArrayList<CarouselItem>()
                state.forEach { data ->
                    list.add(
                        CarouselItem(
                            imageDrawable = data.image,
                            caption = data.id.toString()
                        )
                    )
                }
                binding.imageCarouselAdvertise.setData(list)
                binding.imageCarouselAdvertise.showCaption = false
                binding.imageCarouselAdvertise.carouselListener = object : CarouselListener {
                    override fun onClick(position: Int, carouselItem: CarouselItem) {
                        val advertiseId =
                            (carouselItem.caption ?: state[position].id.toString()).changeInt()
                        showToast(advertiseId.toString())
                    }
                }
                binding.imageCarouselAdvertise.isVisible = state.isNotEmpty()
            }
        }


        lifecycleScope.launchWhenStarted {
            regionViewModel.currentRegion.collectLatest { regionData ->
                if (binding.drawer.isOpen) {
                    binding.drawer.closeDrawers()
                }
                regionData?.let { region ->
                    bindingHeader?.let {
                        Glide.with(this@ScreenHome).load(region.image).into(it.image)
                        it.title.text = region.title
                        it.description.text = region.description
                    }
                    placeViewModel.onEvent(PlaceEvent.LoadPlaceWithType)
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
        lifecycleScope.launch {
            regionViewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is ViewModelRegion.UiEvent.ShowSnackBar -> {
                        showSnackBar(event.message)
                    }

                    is ViewModelRegion.UiEvent.ShowToast -> {
                        showToast(event.message)
                    }
                }
            }
        }
        lifecycleScope.launch {
            advertiseViewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is ViewModelAdvertise.UiEvent.ShowSnackBar -> {
                        showSnackBar(event.message)
                    }

                    is ViewModelAdvertise.UiEvent.ShowToast -> {
                        showToast(event.message)
                    }
                }
            }
        }
    }

    private fun loadViewClickable() {
        binding.navViewHome.setNavigationItemSelectedListener(this)
        binding.title.setNavigationOnClickListener {
            binding.drawer.openDrawer(GravityCompat.START, true)
        }
        bindingHeader?.btnClose?.setOnClickListener {
            binding.drawer.closeDrawers()
        }
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_home_screen_to_screenSearch)
        }
        binding.btnShowAllHistoricalBuilding.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlaceWithType,
                bundleOf(
                    Constants.MOVE_TYPE to Type.HISTORICAL_BUILDING,
                    Constants.MOVE_DATA to getString(R.string.text_historical_building)
                )
            )
        }
        binding.btnShowAllLandscape.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlaceWithType,
                bundleOf(
                    Constants.MOVE_TYPE to Type.LANDSCAPE,
                    Constants.MOVE_DATA to getString(R.string.text_title_landscape)
                )
            )
        }
        binding.btnShowAllReserve.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlaceWithType,
                bundleOf(
                    Constants.MOVE_TYPE to Type.RESERVE,
                    Constants.MOVE_DATA to getString(R.string.text_title_reserve)
                )
            )
        }
        binding.btnShowAllShrine.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlaceWithType,
                bundleOf(
                    Constants.MOVE_TYPE to Type.SHRINE,
                    Constants.MOVE_DATA to getString(R.string.text_title_shrine)
                )
            )
        }
        binding.btnShowAllNearMountain.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlaceWithType,
                bundleOf(
                    Constants.MOVE_TYPE to Type.NEAR_MOUNTAIN,
                    Constants.MOVE_DATA to getString(R.string.text_title_near_mountain)
                )
            )
        }
        binding.btnShowAllNearRivers.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlaceWithType,
                bundleOf(
                    Constants.MOVE_TYPE to Type.NEAR_RIVER,
                    Constants.MOVE_DATA to getString(R.string.text_title_near_rivers)
                )
            )
        }
        nearMountainAdapter.setItemClick { id ->
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlace,
                bundleOf(Constants.MOVE_ID to id)
            )
        }
        nearRiverAdapter.setItemClick { id ->
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlace,
                bundleOf(Constants.MOVE_ID to id)
            )
        }
        reserveAdapter.setItemClick { id ->
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlace,
                bundleOf(Constants.MOVE_ID to id)
            )
        }
        landscapeAdapter.setItemClick { id ->
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlace,
                bundleOf(Constants.MOVE_ID to id)
            )
        }
        shrineAdapter.setItemClick { id ->
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlace,
                bundleOf(Constants.MOVE_ID to id)
            )
        }
        historicalBuildingAdapter.setItemClick { id ->
            findNavController().navigate(
                R.id.action_home_screen_to_screenPlace,
                bundleOf(Constants.MOVE_ID to id)
            )
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.region_andijan -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.ANDIJAN_ID))
            }

            R.id.region_bukhara -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.BUKHARA_ID))
            }

            R.id.region_fergana -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.FERGANA_ID))
            }

            R.id.region_jizzakh -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.JIZZAKH_ID))
            }

            R.id.region_khorezm -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.KHOREZM_ID))
            }

            R.id.region_namangan -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.NAMANGAN_ID))
            }

            R.id.region_navoi -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.NAVOI_ID))
            }

            R.id.region_kashkadarya -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.KASHKADARYA_ID))
            }

            R.id.region_karakalpak -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.KARAKALPAK_ID))
            }

            R.id.region_samarkand -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.SAMARKAND_ID))
            }

            R.id.region_sirdarya -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.SIRDARYA_ID))
            }

            R.id.region_surkhandarya -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.SURKHANDARYA_ID))
            }

            R.id.region_tashkent -> {
                regionViewModel.onEvent(RegionEvent.SetCurrentRegion(RegionConstant.TASHKENT_ID))
            }
        }
        return true
    }
}














