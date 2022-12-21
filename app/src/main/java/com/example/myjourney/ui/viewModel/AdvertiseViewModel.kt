package com.example.myjourney.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjourney.data.model.AdvertiseData_Full
import com.example.myjourney.domen.model.ImageAdvertise
import com.example.myjourney.domen.model.ImagesAdvertise
import com.example.myjourney.domen.use_case.AdvertiseUseCase
import com.example.myjourney.other.ResponseData
import com.example.myjourney.other.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
class AdvertiseViewModel @Inject constructor(private var useCase: AdvertiseUseCase) : ViewModel() {
    private var _state: MutableStateFlow<StateViewModel<AdvertiseViewModelData>> = MutableStateFlow(StateViewModel.Loading(null))
    val state: StateFlow<StateViewModel<AdvertiseViewModelData>> get() = _state.asStateFlow()

    private var _advertise: MutableStateFlow<StateViewModel<AdvertiseData_Full>> = MutableStateFlow(StateViewModel.Loading(null))
    val advertise: StateFlow<StateViewModel<AdvertiseData_Full>> get() = _advertise.asStateFlow()

    fun loadData(id: Int) {
        viewModelScope.launch {
            useCase.getAdvertiseById(id).collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _advertise.value = StateViewModel.Loading(_advertise.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _advertise.value = StateViewModel.Error(data.message, _advertise.value.lastData)
                    }
                    is ResponseData.Success -> {
                        _advertise.value = StateViewModel.Success(data.data)
                    }
                }
            }
        }
    }

    fun loadData() {
        viewModelScope.launch {
            useCase.getMainAdvertise().collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _state.value = StateViewModel.Loading(_state.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _state.value = StateViewModel.Error(data.message, _state.value.lastData)
                    }
                    is ResponseData.Success -> {
                        val newData = _state.value.lastData?.copy() ?: AdvertiseViewModelData(emptyList(), emptyList())
                        newData.mainAdvertises = data.data
                        _state.value = StateViewModel.Success(newData)
                    }
                }
            }
        }
        viewModelScope.launch {
            useCase.getAdditionalAdvertise().collectLatest { data ->
                when (data) {
                    is ResponseData.Loading -> {
                        if (data.isLoading)
                            _state.value = StateViewModel.Loading(_state.value.lastData)
                    }
                    is ResponseData.Error -> {
                        _state.value = StateViewModel.Error(data.message, _state.value.lastData)
                    }
                    is ResponseData.Success -> {
                        val newData = _state.value.lastData?.copy() ?: AdvertiseViewModelData(emptyList(), emptyList())
                        newData.additionAdvertises = data.data
                        _state.value = StateViewModel.Success(newData)
                    }
                }
            }
        }
    }
}

data class AdvertiseViewModelData(
    var mainAdvertises: List<ImageAdvertise>,
    var additionAdvertises: List<ImagesAdvertise>
)