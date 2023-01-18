package com.journey.passenger_presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.passenger_domen.model.Place
import com.journey.passenger_domen.model.PlaceLists
import com.journey.passenger_domen.model.state.PlaceListState
import com.journey.passenger_domen.model.state.PlaceListStateWithType
import com.journey.passenger_domen.useCase.model.PlaceUseCase
import com.journey.passenger_presenter.model.PlaceEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
@HiltViewModel
class ViewModelPlace @Inject constructor(private val useCase: PlaceUseCase) : ViewModel() {
    private val _placesWithType = MutableStateFlow(PlaceLists())
    val placesWithType: StateFlow<PlaceLists> get() = _placesWithType.asStateFlow()

    private val _places = MutableStateFlow<PlaceListState>(PlaceListState.Error("Empty list"))
    val places: StateFlow<PlaceListState> get() = _places.asStateFlow()
    private val _place = MutableStateFlow<Place?>(null)
    val place: StateFlow<Place?> get() = _place.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: PlaceEvent) {
        when (event) {
            PlaceEvent.LoadAllPlace -> {
                viewModelScope.launch {
                    _places.value = useCase.getPlacesList()
                }
            }

            PlaceEvent.LoadPlaceWithType -> {
                viewModelScope.launch {
                    when (val result = useCase.getPlacesListWithType()) {
                        is PlaceListStateWithType.Error -> {
                            _placesWithType.value = PlaceLists()
                            _eventFlow.emit(UiEvent.ShowToast(result.message))
                        }

                        is PlaceListStateWithType.Success -> {
                            _placesWithType.value = result.data
                        }
                    }
                }
            }
            is PlaceEvent.GetPlace -> {
                viewModelScope.launch {
                    _place.value = useCase.getPlace(event.id)
                }
            }

            is PlaceEvent.LoadAllPlaceWithType ->{
                viewModelScope.launch {
                    _places.value = useCase.getPlacesList(event.type)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data class ShowToast(val message: String) : UiEvent()
    }

}