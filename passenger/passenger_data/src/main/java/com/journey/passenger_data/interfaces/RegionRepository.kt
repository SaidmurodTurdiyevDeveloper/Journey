package com.journey.passenger_data.interfaces

import com.journey.common_data.module.RegionDTO

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
interface RegionRepository {
    suspend fun getCurrentRegionId(): Int
    suspend fun getCurrentRegion(): RegionDTO
    suspend fun setCurrentRegionId(id: Int)
}