package com.journey.myjourney.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.myjourney.data.model.RegionData_Full
import com.journey.myjourney.domen.repository.RegionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
@HiltViewModel
class RegionViewModel @Inject constructor(private var repository: RegionRepository) : ViewModel() {
    private var _region: MutableStateFlow<RegionData_Full?> = MutableStateFlow(null)
    val region: StateFlow<RegionData_Full?> = _region.asStateFlow()
    var currentRegion = -1
        private set

    fun loadData() {
        viewModelScope.launch {
            _region.value = repository.getCurrentRegion()
            currentRegion = repository.getCurrentRegion().id
        }
    }

    fun setCurrentRegionId(id: Int) {
        viewModelScope.launch {
            repository.setCurrentRegionId(id)
            loadData()
        }
    }
}