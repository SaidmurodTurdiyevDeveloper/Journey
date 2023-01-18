package com.journey.passenger_domen.useCase

import com.journey.common_utils.other.type.Type
import com.journey.passenger_domen.model.state.PlaceListState
import com.journey.passenger_data.interfaces.PlaceRepository
import com.journey.passenger_data.interfaces.RegionRepository
import com.journey.passenger_domen.utils.getPlacesData

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/24/2023.
 */
class GetPlacesList(
    private val repositoryPlace: PlaceRepository,
    private val repositoryRegion:RegionRepository
) {
    suspend operator fun invoke(type:Type?=null): PlaceListState {
        val regionId=repositoryRegion.getCurrentRegionId()
        val all = when(type){
            Type.NEAR_RIVER -> repositoryPlace.getPlaceNearRiverList(regionId)
            Type.NEAR_MOUNTAIN -> repositoryPlace.getPlaceNearMountainList(regionId)
            Type.HISTORICAL_BUILDING -> repositoryPlace.getPlaceHistoricalBuildingList(regionId)
            Type.LANDSCAPE ->repositoryPlace.getPlaceLandscapeList(regionId)
            Type.SHRINE -> repositoryPlace.getPlaceShrineList(regionId)
            Type.RESERVE -> repositoryPlace.getPlaceReserveList(regionId)
            null -> repositoryPlace.getPlaceAllList()
        }
        return if (all.isEmpty()){
            PlaceListState.Error("Any place can not find")
        } else
            PlaceListState.Success(
                data = all.getPlacesData()
            )
    }
}