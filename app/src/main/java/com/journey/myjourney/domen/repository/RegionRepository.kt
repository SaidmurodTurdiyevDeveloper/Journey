package com.journey.myjourney.domen.repository

import com.journey.myjourney.data.model.RegionData_Full

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
interface RegionRepository {
    suspend fun getCurrentRegionId(): Int
    suspend fun getCurrentRegion(): RegionData_Full
    suspend fun setCurrentRegionId(id: Int)
}