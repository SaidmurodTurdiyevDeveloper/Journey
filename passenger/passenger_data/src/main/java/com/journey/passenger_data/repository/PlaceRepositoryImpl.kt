package com.journey.passenger_data.repository

import com.journey.common_data.module.PlaceDTO
import com.journey.common_data.module.TYPE
import com.journey.common_data.source.local.default.DefaultPlaceList
import com.journey.passenger_data.interfaces.PlaceRepository

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
class PlaceRepositoryImpl(
    private var defaultPlaceList: DefaultPlaceList
) : PlaceRepository {
    override suspend fun getPlaceNearRiverList(regionId: Int): List<PlaceDTO> {
        val list = defaultPlaceList.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.NEAR_RIVER
            else
                data.regionId == regionId && data.type_place == TYPE.NEAR_RIVER
        }
        return list
    }

    override suspend fun getPlaceLandscapeList(regionId: Int): List<PlaceDTO> {
        val list = defaultPlaceList.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.LANDSCAPE
            else
                data.regionId == regionId && data.type_place == TYPE.LANDSCAPE
        }
        return list
    }

    override suspend fun getPlaceNearMountainList(regionId: Int): List<PlaceDTO> {
        val list = defaultPlaceList.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.NEAR_MOUNTAIN
            else
                data.regionId == regionId && data.type_place == TYPE.NEAR_MOUNTAIN
        }
        return list
    }

    override suspend fun getPlaceHistoricalBuildingList(regionId: Int): List<PlaceDTO> {
        val list = defaultPlaceList.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.HISTORICAL_BUILDING
            else
                data.regionId == regionId && data.type_place == TYPE.HISTORICAL_BUILDING
        }
        return list
    }

    override suspend fun getPlaceShrineList(regionId: Int): List<PlaceDTO> {
        val list = defaultPlaceList.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.SHRINE
            else
                data.regionId == regionId && data.type_place == TYPE.SHRINE
        }
        return list
    }

    override suspend fun getPlaceAllList(): List<PlaceDTO> {
        return defaultPlaceList.getList()
    }

    override suspend fun getPlaceReserveList(regionId: Int): List<PlaceDTO> {
        val list = defaultPlaceList.getList().filter { data ->
            if (regionId == -1)
                data.type_place == TYPE.RESERVE
            else
                data.regionId == regionId && data.type_place == TYPE.RESERVE
        }
        return list
    }

    override suspend fun getPlaceById(placeId: Int): PlaceDTO {
        return defaultPlaceList.getList().first { place ->
            place.id == placeId
        }
    }
}