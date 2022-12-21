package com.example.myjourney.domen.use_case.impl

import com.example.myjourney.data.model.PlaceData_Full
import com.example.myjourney.domen.model.PlaceData
import com.example.myjourney.domen.model.PlaceJourneyData
import com.example.myjourney.domen.repository.PlaceRepository
import com.example.myjourney.domen.repository.RegionRepository
import com.example.myjourney.domen.use_case.PlaceUseCase
import com.example.myjourney.other.ResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
class PlaceUseCaseImpl @Inject constructor(private var repositoryPlace: PlaceRepository, private var repositoryRegion: RegionRepository) : PlaceUseCase {
    override fun getPlaceNearRiverList(limit: Int): Flow<ResponseData<List<PlaceData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repositoryPlace.getPlaceNearRiverList(repositoryRegion.getCurrentRegionId())
            if (limit in 0 until list.size)
                emit(ResponseData.Success(list.subList(0, limit)))
            else
                emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }


    override fun getPlaceLandscapeList(limit: Int): Flow<ResponseData<List<PlaceData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repositoryPlace.getPlaceLandscapeList(repositoryRegion.getCurrentRegionId())
            if (limit in 0 until list.size)
                emit(ResponseData.Success(list.subList(0, limit)))
            else
                emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun getPlaceNearMountainList(limit: Int): Flow<ResponseData<List<PlaceData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repositoryPlace.getPlaceNearMountainList(repositoryRegion.getCurrentRegionId())
            if (limit in 0 until list.size)
                emit(ResponseData.Success(list.subList(0, limit)))
            else
                emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun getPlaceHistoricalBuildingList(limit: Int): Flow<ResponseData<List<PlaceData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repositoryPlace.getPlaceHistoricalBuildingList(repositoryRegion.getCurrentRegionId())
            if (limit in 0 until list.size)
                emit(ResponseData.Success(list.subList(0, limit)))
            else
                emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun getPlaceShrineList(limit: Int): Flow<ResponseData<List<PlaceData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repositoryPlace.getPlaceShrineList(repositoryRegion.getCurrentRegionId())
            if (limit in 0 until list.size)
                emit(ResponseData.Success(list.subList(0, limit)))
            else
                emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun getPlaceAllList(): Flow<ResponseData<List<PlaceData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repositoryPlace.getPlaceAllList()
            emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun getPlaceReserveList(limit: Int): Flow<ResponseData<List<PlaceData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repositoryPlace.getPlaceReserveList(repositoryRegion.getCurrentRegionId())
            emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun getPlaceJourneyList(): Flow<ResponseData<List<PlaceJourneyData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repositoryPlace.getPlaceJourneyList()
            emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun setPlaceJourney(placeId: Int): Flow<ResponseData<PlaceData_Full>> = flow {
        emit(ResponseData.Loading(true))
        try {
            repositoryPlace.setPlaceJourney(placeId)
            val data = repositoryPlace.getPlaceById(placeId)
            emit(ResponseData.Success(data))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun deletePlaceJourney(placeId: Int): Flow<ResponseData<List<PlaceJourneyData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            repositoryPlace.deletePlaceJourney(placeId)
            val list = repositoryPlace.getPlaceJourneyList()
            emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun clearPlaceJourneyList(): Flow<ResponseData<List<PlaceJourneyData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            repositoryPlace.clearPlaceJourneyList()
            val list = repositoryPlace.getPlaceJourneyList()
            emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun updatePlaceJourney(placeId: Int, isGone: Int): Flow<ResponseData<List<PlaceJourneyData>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            repositoryPlace.updatePlaceJourney(placeId, isGone)
            val list = repositoryPlace.getPlaceJourneyList()
            emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun getPlaceById(placeId: Int): Flow<ResponseData<PlaceData_Full>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val data = repositoryPlace.getPlaceById(placeId)
            emit(ResponseData.Success(data))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

}