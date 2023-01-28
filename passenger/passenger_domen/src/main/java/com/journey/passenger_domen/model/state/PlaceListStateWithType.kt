package com.journey.passenger_domen.model.state

import com.journey.passenger_domen.model.PlaceLists

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
sealed class PlaceListStateWithType {
    data class Error(val message: String) : PlaceListStateWithType()
    data class Success(val data: PlaceLists) : PlaceListStateWithType()
}
