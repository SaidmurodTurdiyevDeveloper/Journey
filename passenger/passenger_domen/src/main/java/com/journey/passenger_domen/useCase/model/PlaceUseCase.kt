package com.journey.passenger_domen.useCase.model

import com.journey.passenger_domen.useCase.GetPlace
import com.journey.passenger_domen.useCase.GetPlacesList
import com.journey.passenger_domen.useCase.GetPlacesListWithType

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
data class PlaceUseCase(
    val getPlace: GetPlace,
    val getPlacesList: GetPlacesList,
    val getPlacesListWithType: GetPlacesListWithType
)
