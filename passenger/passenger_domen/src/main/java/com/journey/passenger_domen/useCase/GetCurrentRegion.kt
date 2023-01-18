package com.journey.passenger_domen.useCase

import com.journey.passenger_data.interfaces.RegionRepository
import com.journey.passenger_domen.model.RegionData
import com.journey.passenger_domen.model.state.RegionState
import com.journey.passenger_domen.model.toRegion

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/30/2023 6:09 PM for My Journey.
 */
class GetCurrentRegion(private val repositoryRegion: RegionRepository) {
    suspend operator fun invoke(): RegionData = repositoryRegion.getCurrentRegion().toRegion()

}