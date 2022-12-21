package com.example.myjourney.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjourney.data.model.PlaceData_Full
import com.example.myjourney.data.model.TYPE
import com.example.myjourney.domen.model.PlaceData
import com.example.myjourney.domen.use_case.PlaceUseCase
import com.example.myjourney.other.Constants
import com.example.myjourney.other.ResponseData
import com.example.myjourney.other.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 26/11/2022.
 */
@HiltViewModel
class PlaceViewModel @Inject constructor(private var useCase: PlaceUseCase) : ViewModel() {
    private var _stateHome: MutableStateFlow<StateViewModel<PlaceViewModelData>> = MutableStateFlow(StateViewModel.Loading(null))
    val stateHome: StateFlow<StateViewModel<PlaceViewModelData>> get() = _stateHome.asStateFlow()

    private var _statePlaceList: MutableStateFlow<StateViewModel<List<PlaceData>>> = MutableStateFlow(StateViewModel.Loading(null))
    val statePlaceList: StateFlow<StateViewModel<List<PlaceData>>> get() = _statePlaceList.asStateFlow()

    private var _statePlaceItem: MutableStateFlow<StateViewModel<PlaceData_Full>> = MutableStateFlow(StateViewModel.Loading(null))
    val statePlaceItem: StateFlow<StateViewModel<PlaceData_Full>> get() = _statePlaceItem.asStateFlow()

    fun loadDataHomeScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPlaceNearRiverList(10).collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _stateHome.value = StateViewModel.Loading(_stateHome.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _stateHome.value = StateViewModel.Error(data.message, _stateHome.value.lastData)
                    }
                    is ResponseData.Success -> {
                        val newData = _stateHome.value.lastData?.copy() ?: PlaceViewModelData(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
                        newData.nearRiverPlaceList = data.data
                        _stateHome.value = StateViewModel.Success(newData)
                    }
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPlaceLandscapeList(10).collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _stateHome.value = StateViewModel.Loading(_stateHome.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _stateHome.value = StateViewModel.Error(data.message, _stateHome.value.lastData)
                    }
                    is ResponseData.Success -> {
                        val newData = _stateHome.value.lastData?.copy() ?: PlaceViewModelData(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
                        newData.landscapePlaceList = data.data
                        _stateHome.value = StateViewModel.Success(newData)
                    }
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPlaceNearMountainList(10).collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _stateHome.value = StateViewModel.Loading(_stateHome.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _stateHome.value = StateViewModel.Error(data.message, _stateHome.value.lastData)
                    }
                    is ResponseData.Success -> {
                        val newData = _stateHome.value.lastData?.copy() ?: PlaceViewModelData(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
                        newData.nearMountainPlaceList = data.data
                        _stateHome.value = StateViewModel.Success(newData)
                    }
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPlaceHistoricalBuildingList(10).collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _stateHome.value = StateViewModel.Loading(_stateHome.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _stateHome.value = StateViewModel.Error(data.message, _stateHome.value.lastData)
                    }
                    is ResponseData.Success -> {
                        val newData = _stateHome.value.lastData?.copy() ?: PlaceViewModelData(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
                        newData.historicalBuildingPlaceList = data.data
                        _stateHome.value = StateViewModel.Success(newData)
                    }
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPlaceShrineList(10).collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _stateHome.value = StateViewModel.Loading(_stateHome.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _stateHome.value = StateViewModel.Error(data.message, _stateHome.value.lastData)
                    }
                    is ResponseData.Success -> {
                        val newData = _stateHome.value.lastData?.copy() ?: PlaceViewModelData(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
                        newData.shrinePlaceList = data.data
                        _stateHome.value = StateViewModel.Success(newData)
                    }
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPlaceReserveList(10).collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _stateHome.value = StateViewModel.Loading(_stateHome.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _stateHome.value = StateViewModel.Error(data.message, _stateHome.value.lastData)
                    }
                    is ResponseData.Success -> {
                        val newData = _stateHome.value.lastData?.copy() ?: PlaceViewModelData(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
                        newData.reservePlaceList = data.data
                        _stateHome.value = StateViewModel.Success(newData)
                    }
                }
            }
        }
    }

    fun addJourney(id: Int) {
        //you must write here logic
        viewModelScope.launch(Dispatchers.IO) {
            useCase.setPlaceJourney(id).collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _statePlaceItem.value = StateViewModel.Loading(_statePlaceItem.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _statePlaceItem.value = StateViewModel.Error(data.message, _statePlaceItem.value.lastData)
                    }
                    is ResponseData.Success -> {
                        _statePlaceItem.value = StateViewModel.Success(data.data)
                    }
                }
            }
        }
    }

    fun deleteJourney(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deletePlaceJourney(id)
        }
    }

    fun loadDataPlaceWithTypeScreen(type: TYPE) {
        viewModelScope.launch(Dispatchers.IO) {
            when (type) {
                TYPE.NEAR_RIVER -> useCase.getPlaceNearRiverList()
                TYPE.NEAR_MOUNTAIN -> useCase.getPlaceNearMountainList()
                TYPE.LANDSCAPE -> useCase.getPlaceLandscapeList()
                TYPE.HISTORICAL_BUILDING -> useCase.getPlaceHistoricalBuildingList()
                TYPE.SHRINE -> useCase.getPlaceShrineList()
                TYPE.RESERVE -> useCase.getPlaceReserveList()
            }.collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _statePlaceList.value = StateViewModel.Loading(_statePlaceList.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _statePlaceList.value = StateViewModel.Error(data.message, _statePlaceList.value.lastData)
                    }
                    is ResponseData.Success -> {
                        _statePlaceList.value = StateViewModel.Success(data.data)
                    }
                }
            }
        }
    }

    fun loadDataScreenSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPlaceAllList().collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _statePlaceList.value = StateViewModel.Loading(_statePlaceList.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _statePlaceList.value = StateViewModel.Error(data.message, _statePlaceList.value.lastData)
                    }
                    is ResponseData.Success -> {
                        _statePlaceList.value = StateViewModel.Success(data.data)
                    }
                }
            }
        }
    }

    fun loadDataPlaceScreen(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPlaceById(id).collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _statePlaceItem.value = StateViewModel.Loading(_statePlaceItem.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _statePlaceItem.value = StateViewModel.Error(data.message, _statePlaceItem.value.lastData)
                    }
                    is ResponseData.Success -> {
                        _statePlaceItem.value = StateViewModel.Success(data.data)
                    }
                }
            }

        }
    }
}

data class PlaceViewModelData(
    var nearRiverPlaceList: List<PlaceData>,
    var landscapePlaceList: List<PlaceData>,
    var nearMountainPlaceList: List<PlaceData>,
    var historicalBuildingPlaceList: List<PlaceData>,
    var shrinePlaceList: List<PlaceData>,
    var reservePlaceList: List<PlaceData>
)
