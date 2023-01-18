package com.journey.myjourney.ui.screen.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.myjourney.R
import com.journey.myjourney.data.model.TYPE
import com.journey.myjourney.databinding.ScreenHomeBinding
import com.journey.myjourney.other.Constants
import com.journey.myjourney.other.StateViewModel
import com.journey.myjourney.other.extention.showToast
import com.journey.myjourney.ui.adapter.AdvertiseAdapter
import com.journey.myjourney.ui.adapter.PlacesAdapter
import com.journey.myjourney.ui.viewModel.AdvertiseViewModel
import com.journey.myjourney.ui.viewModel.PlaceViewModel
import com.journey.myjourney.ui.viewModel.RegionViewModel
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
                            val isNotEmpty = list.isNotEmpty()
                            val isSingleItem = list.size == 1
                            binding.cvReserve.isVisible = isNotEmpty
                            binding.singleItemReserve.isVisible = isSingleItem
                            binding.rvReserve.isVisible = isSingleItem.not()
                            binding.bottomReserve.isVisible = isSingleItem.not()
                            if (isSingleItem) {
                                val data = list.first()
                                binding.singleItemImageReserve.setImageResource(data.picture)
                                binding.singleItemNameReserve.text = data.name
                                binding.singleItemReserve.setOnClickListener {
                                    openPlace(data.id)
                                }
                            } else if (isNotEmpty) reserveAdapter.differ.submitList(list)
                        }
                        state.data.shrinePlaceList.let { list ->
                            val isNotEmpty = list.isNotEmpty()
                            val isSingleItem = list.size == 1
                            binding.cvShrine.isVisible = isNotEmpty
                            binding.singleItemShrine.isVisible = isSingleItem
                            binding.rvShrine.isVisible = isSingleItem.not()
                            binding.bottomShrine.isVisible = isSingleItem.not()
                            if (isSingleItem) {
                                val data = list.first()
                                binding.singleItemImageShrine.setImageResource(data.picture)
                                binding.singleItemNameShrine.text = data.name
                                binding.singleItemShrine.setOnClickListener {
                                    openPlace(data.id)
                                }
                            } else if (isNotEmpty)
                                shrineAdapter.differ.submitList(list)
                        }
                        state.data.nearRiverPlaceList.let { list ->
                            val isNotEmpty = list.isNotEmpty()
                            val isSingleItem = list.size == 1
                            binding.cvNearRivers.isVisible = isNotEmpty
                            binding.singleItemNearRiver.isVisible = isSingleItem
                            binding.rvNearRivers.isVisible = isSingleItem.not()
                            binding.bottomNearRivers.isVisible = isSingleItem.not()
                            if (isSingleItem) {
                                val data = list.first()
                                binding.singleItemImageNearRiver.setImageResource(data.picture)
                                binding.singleItemNameNearRiver.text = data.name
                                binding.singleItemNearRiver.setOnClickListener {
                                    openPlace(data.id)
                                }
                            } else if (isNotEmpty)
                                nearRiverAdapter.differ.submitList(list)
                        }
                        state.data.nearMountainPlaceList.let { list ->
                            val isNotEmpty = list.isNotEmpty()
                            val isSingleItem = list.size == 1
                            binding.cvNearMountain.isVisible = isNotEmpty
                            binding.singleItemNearMountain.isVisible = isSingleItem
                            binding.rvNearMountain.isVisible = isSingleItem.not()
                            binding.bottomNearMountain.isVisible = isSingleItem.not()
                            if (isSingleItem) {
                                val data = list.first()
                                binding.singleItemImageNearMountain.setImageResource(data.picture)
                                binding.singleItemNameNearMountain.text = data.name
                                binding.singleItemNearMountain.setOnClickListener {
                                    openPlace(data.id)
                                }
                            } else if (isNotEmpty)
                                nearMountainAdapter.differ.submitList(list)
                        }
                        state.data.landscapePlaceList.let { list ->
                            val isNotEmpty = list.isNotEmpty()
                            val isSingleItem = list.size == 1
                            binding.cvLandscape.isVisible = isNotEmpty
                            binding.singleItemLandscape.isVisible = isSingleItem
                            binding.rvLandscape.isVisible = isSingleItem.not()
                            binding.bottomLandscape.isVisible = isSingleItem.not()
                            if (isSingleItem) {
                                val data = list.first()
                                binding.singleItemImageLandscape.setImageResource(data.picture)
                                binding.singleItemNameLandscape.text = data.name
                                binding.singleItemLandscape.setOnClickListener {
                                    openPlace(data.id)
                                }
                            } else if (isNotEmpty)
                                landscapeAdapter.differ.submitList(list)
                        }
                        state.data.historicalBuildingPlaceList.let { list ->
                            val isNotEmpty = list.isNotEmpty()
                            val isSingleItem = list.size == 1
                            binding.cvHistoricalBuilding.isVisible = isNotEmpty
                            binding.singleItemHistoricalBuilding.isVisible = isSingleItem
                            binding.rvHistoricalBuilding.isVisible = isSingleItem.not()
                            binding.bottomHistoricalBuilding.isVisible = isSingleItem.not()
                            if (isSingleItem) {
                                val data = list.first()
                                binding.singleItemImageHistoricalBuilding.setImageResource(data.picture)
                                binding.singleItemNameHistoricalBuilding.text = data.name
                                binding.singleItemHistoricalBuilding.setOnClickListener {
                                    openPlace(data.id)
                                }
                            } else if (isNotEmpty)
                                historicalBuildingAdapter.differ.submitList(list)
                        }
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
        binding.btnMore.setOnClickListener {
            val menu = PopupMenu(requireContext(), binding.btnMore)
            menu.menuInflater.inflate(R.menu.home_popup_menu, menu.menu)
            menu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.notification -> showToast("Notification")
                }
                true
            }
            menu.show()
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














