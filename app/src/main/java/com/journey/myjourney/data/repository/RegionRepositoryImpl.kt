package com.journey.myjourney.data.repository

import com.journey.myjourney.data.model.RegionData_Full
import com.journey.myjourney.data.source.local.deafoult.RegionList
import com.journey.myjourney.data.source.local.shared.SharedDatabase
import com.journey.myjourney.domen.repository.RegionRepository
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
class RegionRepositoryImpl @Inject constructor(private var store: SharedDatabase, private var regions: RegionList) : RegionRepository {
    override suspend fun getCurrentRegionId(): Int {
        return store.currentRegion
    }

    override suspend fun getCurrentRegion(): RegionData_Full {
        return regions.getList().first { region ->
            store.currentRegion == region.id
        }
    }

    override suspend fun setCurrentRegionId(id: Int) {
        store.currentRegion = id
    }

}