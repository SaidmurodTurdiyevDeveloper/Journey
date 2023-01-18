package com.journey.passenger_presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.passenger_domen.model.JourneyData
import com.journey.passenger_domen.model.state.JourneysListState
import com.journey.passenger_domen.useCase.model.JourneyUseCase
import com.journey.passenger_presenter.model.JourneyEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
@HiltViewModel
class ViewModelJourney @Inject constructor(private val useCase: JourneyUseCase) : ViewModel() {
    private val _journeyList = MutableStateFlow(emptyList<JourneyData>())
    val journeyList: StateFlow<List<JourneyData>> get() = _journeyList.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: JourneyEvent) {
        when (event) {
            JourneyEvent.ClearJourney -> {
                viewModelScope.launch {
                    _journeyList.value = useCase.clearJourney()
                }
            }

            JourneyEvent.GetJourneyList -> {
                viewModelScope.launch {
                    when (val result = useCase.getJourneysList()) {
                        is JourneysListState.Error -> {
                            _journeyList.value = emptyList()
                            _eventFlow.emit(UiEvent.ShowToast(message = result.message))
                        }

                        is JourneysListState.Success -> {
                            _journeyList.value = result.list
                        }
                    }
                }
            }
            JourneyEvent.DeleteJourneyList -> {
                
            }

            is JourneyEvent.DeleteJourney -> {
                viewModelScope.launch {
                    _journeyList.value = useCase.deleteJourney(event.id)
                }
            }

            is JourneyEvent.AddJourney -> {
                viewModelScope.launch {
                    if (useCase.addJourney(event.placeId)) {
                        _eventFlow.emit(UiEvent.ShowToast("Success"))
                        _eventFlow.emit(UiEvent.CloseScreen)
                    }
                }
            }

            is JourneyEvent.UpdateJourney -> {
                viewModelScope.launch {
                    _journeyList.value = useCase.updateJourney(
                        data = event.data, type = event.type
                    )
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data class ShowToast(val message: String) : UiEvent()
        object CloseScreen : UiEvent()
    }
}