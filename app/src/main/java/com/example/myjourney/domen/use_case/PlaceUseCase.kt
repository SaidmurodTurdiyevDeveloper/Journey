package com.example.myjourney.domen.use_case

import com.example.myjourney.data.model.PlaceData_Full
import com.example.myjourney.domen.model.PlaceData
import com.example.myjourney.domen.model.PlaceJourneyData
import com.example.myjourney.other.ResponseData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
interface PlaceUseCase {
    fun getPlaceNearRiverList(limit:Int=-1): Flow<ResponseData<List<PlaceData>>>
    fun getPlaceLandscapeList(limit: Int=-1): Flow<ResponseData<List<PlaceData>>>
    fun getPlaceNearMountainList(limit: Int=-1): Flow<ResponseData<List<PlaceData>>>
    fun getPlaceHistoricalBuildingList(limit: Int=-1): Flow<ResponseData<List<PlaceData>>>
    fun getPlaceShrineList(limit: Int=-1): Flow<ResponseData<List<PlaceData>>>
    fun getPlaceReserveList(limit: Int=-1): Flow<ResponseData<List<PlaceData>>>
    fun getPlaceAllList(): Flow<ResponseData<List<PlaceData>>>
    fun getPlaceJourneyList(): Flow<ResponseData<List<PlaceJourneyData>>>
    fun setPlaceJourney(placeId: Int): Flow<ResponseData<PlaceData_Full>>
    fun deletePlaceJourney(placeId: Int): Flow<ResponseData<List<PlaceJourneyData>>>
    fun clearPlaceJourneyList(): Flow<ResponseData<List<PlaceJourneyData>>>
    fun updatePlaceJourney(placeId: Int,isGone:Int): Flow<ResponseData<List<PlaceJourneyData>>>
    fun getPlaceById(placeId: Int): Flow<ResponseData<PlaceData_Full>>
}