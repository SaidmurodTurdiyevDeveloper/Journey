package com.journey.passenger_data.repository

import com.journey.common_data.source.local.default.DefaultPlaceList
import com.journey.common_data.source.local.shared.SharedDatabase
import com.journey.passenger_data.interfaces.JourneyRepository
import com.journey.passenger_data.source.local.room.dao.JourneyDao
import com.journey.passenger_data.source.local.room.entity.JourneyEntity

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/24/2023.
 */
class JourneyRepositoryImpl(private val localStore: SharedDatabase, private val roomStore: JourneyDao, private val defaultPlacesList: DefaultPlaceList) :
    JourneyRepository {

    override suspend fun setCurrentJourney(placeId: Int) {
        localStore.currentRegion = placeId
    }

    override suspend fun addJourney(data: JourneyEntity): Boolean {
        return roomStore.insertData(data) >= 0
    }

    override suspend fun update(data: JourneyEntity) {
        roomStore.update(data)
    }

    override suspend fun getCurrentJourneyId(): Int = localStore.currentJourney

    override suspend fun deleteJourney(placeId: Int): Boolean {
        val journey = roomStore.getJourneyById(placeId)
        return roomStore.deleteJourney(journey) >= 0
    }

    override suspend fun clearJourneyList(): Boolean {
        val listJourney = roomStore.getList()
        if (listJourney.isEmpty())
            return true
        roomStore.deleteListJourney(listJourney)
        return listJourney.size > roomStore.getList().size
    }

    override suspend fun getJourneyList(): List<JourneyEntity> {
        return  roomStore.getList()
    }
}