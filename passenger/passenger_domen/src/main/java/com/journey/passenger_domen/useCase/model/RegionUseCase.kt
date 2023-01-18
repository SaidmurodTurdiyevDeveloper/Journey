package com.journey.passenger_domen.useCase.model

import com.journey.passenger_domen.useCase.GetCurrentRegion
import com.journey.passenger_domen.useCase.SetCurrentRegion

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/30/2023 6:29 PM for My Journey.
 */
data class RegionUseCase(
    val getRegion: GetCurrentRegion,
    val setRegion: SetCurrentRegion
)
