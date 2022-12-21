package com.example.myjourney.domen.repository

import com.example.myjourney.data.model.RegionData_Full

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
interface RegionRepository {
    suspend fun getCurrentRegionId(): Int
    suspend fun getCurrentRegion(): RegionData_Full
    suspend fun setCurrentRegionId(id: Int)
}