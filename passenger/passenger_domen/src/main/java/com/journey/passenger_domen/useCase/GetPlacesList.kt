package com.journey.passenger_domen.useCase

import com.journey.passenger_domen.model.state.PlaceListState
import com.journey.passenger_data.interfaces.PlaceRepository
import com.journey.passenger_domen.utils.getPlacesData

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/24/2023.
 */
class GetPlacesList(
    private val repositoryPlace: PlaceRepository
) {
    suspend operator fun invoke(): PlaceListState {
        val all = repositoryPlace.getPlaceAllList()
        return if (all.isEmpty()){
            PlaceListState.Error("Any place can not find")
        } else
            PlaceListState.Success(
                data = all.getPlacesData()
            )
    }
}