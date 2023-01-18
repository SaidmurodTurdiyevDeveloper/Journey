package com.journey.passenger_domen.useCase

import com.journey.passenger_data.interfaces.RegionRepository
import com.journey.passenger_domen.model.state.RegionState
import com.journey.passenger_domen.model.toRegion

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/30/2023 6:11 PM for My Journey.
 */
class SetCurrentRegion(private val repositoryRegion: RegionRepository) {

    suspend operator fun invoke(regionId: Int): RegionState {
        if (regionId < 0 || regionId >= 12)
            return RegionState.Error("Wrong region id")
        repositoryRegion.setCurrentRegionId(regionId)
        val region = repositoryRegion.getCurrentRegion().toRegion()
        return RegionState.Success(data = region)
    }
}