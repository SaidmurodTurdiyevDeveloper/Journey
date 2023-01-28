package com.journey.passenger_presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.passenger_domen.model.Advertise
import com.journey.passenger_domen.model.ImageAdvertise
import com.journey.passenger_domen.model.ImagesAdvertise
import com.journey.passenger_domen.model.state.AdvertisesListState
import com.journey.passenger_domen.model.state.PlaceListState
import com.journey.passenger_domen.model.state.PlaceListStateWithType
import com.journey.passenger_domen.useCase.model.AdvertiseUseCase
import com.journey.passenger_presenter.model.AdvertiseEvent
import com.journey.passenger_presenter.model.PlaceEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/28/2023 9:05 PM for My Journey.
 */
@HiltViewModel
class ViewModelAdvertise @Inject constructor(
    private var useCase: AdvertiseUseCase
) : ViewModel() {
    private val _imageAdvertiseList = MutableStateFlow<List<ImageAdvertise>>(emptyList())
    val imageAdvertiseList: StateFlow<List<ImageAdvertise>> get() = _imageAdvertiseList.asStateFlow()

    private val _imagesAdvertiseList = MutableStateFlow<List<ImagesAdvertise>>(emptyList())
    val imagesAdvertiseList: StateFlow<List<ImagesAdvertise>> get() = _imagesAdvertiseList.asStateFlow()

    private val _advertiseList = MutableStateFlow<Advertise?>(null)
    val advertiseList: StateFlow<Advertise?> get() = _advertiseList.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AdvertiseEvent) {
        when (event) {
            is AdvertiseEvent.GetAdvertise -> {
                viewModelScope.launch {
                    _advertiseList.value = useCase.getAdvertise(id = event.id)
                }
            }

            AdvertiseEvent.LoadList -> {
                viewModelScope.launch {
                    when (val result = useCase.getAdvertisesList()) {
                        is AdvertisesListState.Error -> {
                            _imagesAdvertiseList.value = emptyList()
                            _imageAdvertiseList.value = emptyList()
                            _eventFlow.emit(UiEvent.ShowToast(result.message))
                        }

                        is AdvertisesListState.Success -> {
                            _imagesAdvertiseList.value = result.multipleImagesAdvertiseList
                            _imageAdvertiseList.value = result.singleImageAdvertiseList
                        }
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data class ShowToast(val message: String) : UiEvent()
    }
}