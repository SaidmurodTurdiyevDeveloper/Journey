package com.journey.passenger_data.interfaces

import com.journey.passenger_data.source.local.room.entity.JourneyEntity

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/24/2023.
 */
interface JourneyRepository {
    suspend fun getJourneyList(): List<JourneyEntity>
    suspend fun setCurrentJourney(placeId: Int)
    suspend fun addJourney(data: JourneyEntity):Boolean
    suspend fun update(data: JourneyEntity)
    suspend fun getCurrentJourneyId(): Int
    suspend fun deleteJourney(placeId: Int): Boolean
    suspend fun clearJourneyList(): Boolean
}