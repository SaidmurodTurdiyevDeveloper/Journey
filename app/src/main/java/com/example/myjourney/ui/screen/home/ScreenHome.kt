package com.example.myjourney.ui.screen.home

import android.os.Bundle
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
import com.example.myjourney.R
import com.example.myjourney.data.model.TYPE
import com.example.myjourney.databinding.ScreenHomeBinding
import com.example.myjourney.other.Constants
import com.example.myjourney.other.StateViewModel
import com.example.myjourney.ui.adapter.AdvertiseAdapter
import com.example.myjourney.ui.adapter.PlacesAdapter
import com.example.myjourney.ui.viewModel.AdvertiseViewModel
import com.example.myjourney.ui.viewModel.PlaceViewModel
import com.example.myjourney.ui.viewModel.RegionViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class ScreenHome : Fragment(R.layout.screen_home), NavigationView.OnNavigationItemSelectedListener {
    private val advertiseViewModel: AdvertiseViewModel by viewModels()
    private val placeViewModel: PlaceViewModel by viewModels()
    private val regionViewModel: RegionViewModel by viewModels()
    private val binding: ScreenHomeBinding by viewBinding()
    private var nearRiverAdapter = PlacesAdapter()
    private var landscapeAdapter = PlacesAdapter()
    private var nearMountainAdapter = PlacesAdapter()
    private var historicalBuildingAdapter = PlacesAdapter()
    private var shrineAdapter = PlacesAdapter()
    private var reserveAdapter = PlacesAdapter()
    private var advertiseAdapter: AdvertiseAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        advertiseViewModel.loadData()
        placeViewModel.loadDataHomeScreen()
        regionViewModel.loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewClickable()
        loadViewDetails()
    }

    private fun loadViewDetails() {
        lifecycleScope.launchWhenStarted {
            advertiseViewModel.state.collectLatest { state ->
                when (state) {
                    is StateViewModel.Loading -> {
                        openLoading()
                    }
                    is StateViewModel.Error -> {
                        showError(state.message)
                    }
                    is StateViewModel.Success -> {
                        state.data.mainAdvertises.forEach { data ->
                            binding.imageCarouselAdvertise.addData(CarouselItem(data.image))
                        }
                        advertiseAdapter = AdvertiseAdapter(requireActivity(), state.data.additionAdvertises)
                        binding.vpFamousPlace.adapter = advertiseAdapter
                        binding.dotsIndicatorFamousPlace.setViewPager2(binding.vpFamousPlace)
                    }
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            regionViewModel.region.collectLatest { data ->
                if (data != null) {
                    binding.navViewHome.getHeaderView(0).findViewById<ImageView>(R.id.image).setImageResource(data.image)
                    binding.navViewHome.getHeaderView(0).findViewById<TextView>(R.id.title).text = data.title
                    binding.navViewHome.getHeaderView(0).findViewById<TextView>(R.id.description).text = data.description
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            placeViewModel.stateHome.collectLatest { state ->
                when (state) {
                    is StateViewModel.Loading -> {
                        openLoading()
                    }
                    is StateViewModel.Error -> {
                        showError(state.message)
                    }
                    is StateViewModel.Success -> {
                        state.data.reservePlaceList.let { list ->
                            if (list.isEmpty())
                                binding.cvReserve.isVisible = false
                            else
                                reserveAdapter.differ.submitList(list)
                        }
                        state.data.shrinePlaceList.let { list ->
                            if (list.isEmpty())
                                binding.cvShrine.isVisible = false
                            else
                                shrineAdapter.differ.submitList(list)
                        }
                        state.data.nearRiverPlaceList.let { list ->
                            if (list.isEmpty())
                                binding.cvNearRivers.isVisible = false
                            else
                                nearRiverAdapter.differ.submitList(list)
                        }
                        state.data.nearMountainPlaceList.let { list ->
                            if (list.isEmpty())
                                binding.cvNearMountain.isVisible = false
                            else
                                nearMountainAdapter.differ.submitList(list)
                        }
                        state.data.landscapePlaceList.let { list ->
                            if (list.isEmpty())
                                binding.cvLandscape.isVisible = false
                            else
                                landscapeAdapter.differ.submitList(list)
                        }
                        state.data.historicalBuildingPlaceList.let { list ->
                            if (list.isEmpty())
                                binding.cvHistoricalBuilding.isVisible = false
                            else
                                historicalBuildingAdapter.differ.submitList(list)
                        }
                        historicalBuildingAdapter.differ.submitList(state.data.historicalBuildingPlaceList)
                    }
                }
            }
        }
        binding.rvLandscape.adapter = landscapeAdapter
        binding.rvHistoricalBuilding.adapter = historicalBuildingAdapter
        binding.rvNearMountain.adapter = nearMountainAdapter
        binding.rvReserve.adapter = reserveAdapter
        binding.rvNearRivers.adapter = nearRiverAdapter
        binding.rvShrine.adapter = shrineAdapter
    }

    private fun loadViewClickable() {
        binding.navViewHome.setNavigationItemSelectedListener(this)
        binding.title.setNavigationOnClickListener {
            binding.drawer.openDrawer(GravityCompat.START, true)
        }
        binding.navViewHome.getHeaderView(0).findViewById<ImageView>(R.id.btn_close).setOnClickListener {
            binding.drawer.closeDrawer(GravityCompat.START, true)
        }

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_home_screen_to_screenSearch)
        }
        binding.btnShowAllLandscape.setOnClickListener {
            openPlaceAll(TYPE.LANDSCAPE)
        }
        binding.btnShowAllReserve.setOnClickListener {
            openPlaceAll(TYPE.RESERVE)
        }
        binding.btnShowAllShrine.setOnClickListener {
            openPlaceAll(TYPE.SHRINE)
        }
        binding.btnShowAllHistoricalBuilding.setOnClickListener {
            openPlaceAll(TYPE.HISTORICAL_BUILDING)
        }
        binding.btnShowAllNearMountain.setOnClickListener {
            openPlaceAll(TYPE.NEAR_MOUNTAIN)
        }
        binding.btnShowAllNearRivers.setOnClickListener {
            openPlaceAll(TYPE.NEAR_RIVER)
        }
        historicalBuildingAdapter.setItemClick { id ->
            openPlace(id)
        }
        landscapeAdapter.setItemClick { id ->
            openPlace(id)
        }
        reserveAdapter.setItemClick { id ->
            openPlace(id)
        }
        nearMountainAdapter.setItemClick { id ->
            openPlace(id)
        }
        nearRiverAdapter.setItemClick { id ->
            openPlace(id)
        }
        shrineAdapter.setItemClick { id ->
            openPlace(id)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.region_andijon -> {
                regionViewModel.setCurrentRegionId(0)
            }
            R.id.region_bukhara -> {
                regionViewModel.setCurrentRegionId(1)
            }
            R.id.region_fergana -> {
                regionViewModel.setCurrentRegionId(2)
            }
            R.id.region_jizzakh -> {
                regionViewModel.setCurrentRegionId(3)
            }
            R.id.region_khorazm -> {
                regionViewModel.setCurrentRegionId(4)
            }
            R.id.region_namangan -> {
                regionViewModel.setCurrentRegionId(5)
            }
            R.id.region_navoi -> {
                regionViewModel.setCurrentRegionId(6)
            }
            R.id.region_qashqadaro -> {
                regionViewModel.setCurrentRegionId(7)
            }
            R.id.region_qorqalpoq -> {
                regionViewModel.setCurrentRegionId(8)
            }
            R.id.region_samarand -> {
                regionViewModel.setCurrentRegionId(9)
            }
            R.id.region_sirdaryo -> {
                regionViewModel.setCurrentRegionId(10)
            }
            R.id.region_surkhandaryo -> {
                regionViewModel.setCurrentRegionId(11)
            }
            R.id.region_tashkent -> {
                regionViewModel.setCurrentRegionId(12)
            }
        }
        placeViewModel.loadDataHomeScreen()
        binding.drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openPlace(id: Int) {
        findNavController().navigate(R.id.action_home_screen_to_screenPlace, bundleOf(Constants.MOVE_ID to id))
    }

    private fun openPlaceAll(type: TYPE) {
        findNavController().navigate(
            R.id.action_home_screen_to_screenPlaceWithType,
            bundleOf(
                Constants.MOVE_TYPE to type
            )
        )
    }

    private fun openAdvertise(id: Int) {

    }

    private fun openLoading() {

    }

    private fun showError(message: String) {

    }
}














