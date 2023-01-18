package com.example.myjourney.data.repository

import com.example.myjourney.data.model.PlaceData_Full
import com.example.myjourney.data.model.TYPE
import com.example.myjourney.data.source.local.deafoult.PlaceList
import com.example.myjourney.data.source.local.room.dao.JourneyDao
import com.example.myjourney.data.source.local.room.entity.JourneyEntity
import com.example.myjourney.domen.model.PlaceData
import com.example.myjourney.domen.model.PlaceJourneyData
import com.example.myjourney.domen.repository.PlaceRepository
import com.example.myjourney.other.Constants
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
class PlaceRepositoryImpl @Inject constructor(private var places: PlaceList, private var db: JourneyDao) : PlaceRepository {
    override suspend fun getPlaceNearRiverList(regionId: Int): List<PlaceData> {
        val list = places.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.NEAR_RIVER
            else
                data.regionId == regionId && data.type_place == TYPE.NEAR_RIVER
        }
        return list.map { data ->
            PlaceData(data.id, data.picture, data.name)
        }
    }

    override suspend fun getPlaceLandscapeList(regionId: Int): List<PlaceData> {
        val list = places.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.LANDSCAPE
            else
                data.regionId == regionId && data.type_place == TYPE.LANDSCAPE
        }
        return list.map { data ->
            PlaceData(data.id, data.picture, data.name)
        }
    }

    override suspend fun getPlaceNearMountainList(regionId: Int): List<PlaceData> {
        val list = places.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.NEAR_MOUNTAIN
            else
                data.regionId == regionId && data.type_place == TYPE.NEAR_MOUNTAIN
        }
        return list.map { data ->
            PlaceData(data.id, data.picture, data.name)
        }
    }

    override suspend fun getPlaceHistoricalBuildingList(regionId: Int): List<PlaceData> {
        val list = places.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.HISTORICAL_BUILDING
            else
                data.regionId == regionId && data.type_place == TYPE.HISTORICAL_BUILDING
        }
        return list.map { data ->
            PlaceData(data.id, data.picture, data.name)
        }
    }

    override suspend fun getPlaceShrineList(regionId: Int): List<PlaceData> {
        val list=places.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.SHRINE
            else
                data.regionId == regionId && data.type_place == TYPE.SHRINE
        }
        return list.map { data ->
            PlaceData(data.id, data.picture, data.name)
        }
    }

    override suspend fun getPlaceAllList(): List<PlaceData> {
        return places.getList().map { data ->
            PlaceData(data.id, data.picture, data.name)
        }
    }

    override suspend fun getPlaceReserveList(regionId: Int): List<PlaceData> {
        val list=places.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.RESERVE
            else
                data.regionId == regionId && data.type_place == TYPE.RESERVE
        }
        return list.map { data ->
            PlaceData(data.id, data.picture, data.name)
        }
    }

    override suspend fun getPlaceJourneyList(): List<PlaceJourneyData> {
        val lsJourney = db.getList()
        val finalList = places.getList().filter { place ->
            lsJourney.firstOrNull { journey ->
                place.id == journey.placeId
            } != null
        }
        // you must wriite here is Go type
        return finalList.map { data ->
            PlaceJourneyData(data.id, data.picture, data.name, data.info,Constants.GO)
        }
    }

    override suspend fun setPlaceJourney(placeId: Int): Boolean {
        val lsJourney = db.getList()
        return if (lsJourney.firstOrNull { journey ->
                journey.placeId == placeId
            } == null) {
            db.insertData(JourneyEntity(0, placeId, Constants.GO)) >= 0
        } else
            false
    }

    override suspend fun updatePlaceJourney(placeId: Int, isGone: Int): Boolean {
        val journey = db.getJourneyById(placeId)
        journey.isGone = isGone
        return db.update(journey) >= 0
    }

    override suspend fun deletePlaceJourney(placeId: Int): Boolean {
        val journey = db.getJourneyById(placeId)
        return db.deleteJourney(journey) >= 0
    }

    override suspend fun clearPlaceJourneyList(): Boolean {
        val listJourney = db.getList()
        if (listJourney.isEmpty())
            return true
        db.deleteListJourney(listJourney)
        return listJourney.size > db.getList().size
    }

    override suspend fun getPlaceById(placeId: Int): PlaceData_Full {
        val data = places.getList().first { place ->
            place.id == placeId
        }
        db.getList().forEach { journey ->
            if (journey.placeId == placeId)
                data.isCanAdd = false
        }

        return data
    }
}