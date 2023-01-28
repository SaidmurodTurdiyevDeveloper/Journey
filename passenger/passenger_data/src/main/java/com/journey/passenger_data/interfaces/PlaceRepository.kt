package com.journey.passenger_data.interfaces

import com.journey.common_data.module.PlaceDTO

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
interface PlaceRepository {
    suspend fun getPlaceNearRiverList(regionId: Int): List<PlaceDTO>
    suspend fun getPlaceLandscapeList(regionId: Int): List<PlaceDTO>
    suspend fun getPlaceNearMountainList(regionId: Int): List<PlaceDTO>
    suspend fun getPlaceHistoricalBuildingList(regionId: Int): List<PlaceDTO>
    suspend fun getPlaceShrineList(regionId: Int): List<PlaceDTO>
    suspend fun getPlaceReserveList(regionId: Int): List<PlaceDTO>
    suspend fun getPlaceAllList(): List<PlaceDTO>
    suspend fun getPlaceById(placeId: Int): PlaceDTO
}