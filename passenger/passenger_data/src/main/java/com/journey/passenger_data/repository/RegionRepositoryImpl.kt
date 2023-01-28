package com.journey.passenger_data.repository

import com.journey.common_data.module.RegionDTO
import com.journey.common_data.source.local.default.DefaultRegionList
import com.journey.common_data.source.local.shared.SharedDatabase
import com.journey.passenger_data.interfaces.RegionRepository

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
class RegionRepositoryImpl(
    private var localStore: SharedDatabase,
    private var defaultRegionList: DefaultRegionList
) : RegionRepository {
    override suspend fun getCurrentRegionId(): Int {
        return localStore.currentRegion
    }

    override suspend fun getCurrentRegion(): RegionDTO {
        return defaultRegionList.getList().first { region ->
            localStore.currentRegion == region.id
        }
    }

    override suspend fun setCurrentRegionId(id: Int) {
        localStore.currentRegion = id
    }

}