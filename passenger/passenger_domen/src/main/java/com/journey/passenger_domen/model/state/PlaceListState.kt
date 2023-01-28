package com.journey.passenger_domen.model.state

import com.journey.passenger_domen.model.PlaceData

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
sealed class PlaceListState {
    data class Error(val message: String) : PlaceListState()
    data class Success(val data: List<PlaceData>) : PlaceListState()
}
