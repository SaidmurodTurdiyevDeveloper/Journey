package com.example.myjourney.domen.repository

import com.example.myjourney.data.model.PlaceData_Full
import com.example.myjourney.domen.model.PlaceData
import com.example.myjourney.domen.model.PlaceJourneyData

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
interface PlaceRepository {
    suspend fun getPlaceNearRiverList(regionId: Int): List<PlaceData>
    suspend fun getPlaceLandscapeList(regionId: Int): List<PlaceData>
    suspend fun getPlaceNearMountainList(regionId: Int): List<PlaceData>
    suspend fun getPlaceHistoricalBuildingList(regionId: Int): List<PlaceData>
    suspend fun getPlaceShrineList(regionId: Int): List<PlaceData>
    suspend fun getPlaceReserveList(regionId: Int): List<PlaceData>
    suspend fun getPlaceAllList(): List<PlaceData>
    suspend fun getPlaceJourneyList(): List<PlaceJourneyData>
    suspend fun setPlaceJourney(placeId: Int): Boolean
    suspend fun updatePlaceJourney(placeId: Int, isGone: Int): Boolean
    suspend fun deletePlaceJourney(placeId: Int): Boolean
    suspend fun clearPlaceJourneyList(): Boolean
    suspend fun getPlaceById(placeId: Int): PlaceData_Full
}