package com.journey.passenger_domen.useCase.model

import com.journey.passenger_domen.useCase.GetAdvertise
import com.journey.passenger_domen.useCase.GetAdvertisesList

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
data class AdvertiseUseCase(
    val getAdvertisesList: GetAdvertisesList,
    val getAdvertise: GetAdvertise
)
