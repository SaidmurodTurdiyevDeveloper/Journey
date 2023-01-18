package com.journey.passenger_presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.passenger_domen.model.RegionData
import com.journey.passenger_domen.model.state.RegionState
import com.journey.passenger_domen.useCase.model.RegionUseCase
import com.journey.passenger_presenter.model.RegionEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/30/2023 6:08 PM for My Journey.
 */
@HiltViewModel
class ViewModelRegion @Inject constructor(private val useCase: RegionUseCase) : ViewModel() {
    private val _currentRegion = MutableStateFlow<RegionData?>(null)
    val currentRegion: StateFlow<RegionData?> = _currentRegion.asStateFlow()
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: RegionEvent) {
        when (event) {
            RegionEvent.LoadCurrentRegion -> {
                viewModelScope.launch {
                    _currentRegion.value = useCase.getRegion()
                }
            }

            is RegionEvent.SetCurrentRegion -> {
                viewModelScope.launch {
                    when (val result = useCase.setRegion(regionId = event.id)) {
                        is RegionState.Error -> {
                            _eventFlow.emit(UiEvent.ShowToast(result.message))
                        }

                        is RegionState.Success -> {
                            _currentRegion.value = result.data
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