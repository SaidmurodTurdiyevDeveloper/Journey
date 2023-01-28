package com.journey.passenger_domen.useCase

import com.journey.passenger_domen.model.PlaceLists
import com.journey.passenger_domen.model.state.PlaceListStateWithType
import com.journey.passenger_data.interfaces.PlaceRepository
import com.journey.passenger_data.interfaces.RegionRepository
import com.journey.passenger_domen.utils.getPlacesData

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/24/2023.
 */
class GetPlacesListWithType(
    private val repositoryPlace: PlaceRepository,
    private val repositoryRegion: RegionRepository
) {
    suspend operator fun invoke(): PlaceListStateWithType {
        val currentRegion = repositoryRegion.getCurrentRegionId()
        val nearRiver = repositoryPlace.getPlaceNearRiverList(currentRegion)
        val landscape = repositoryPlace.getPlaceLandscapeList(currentRegion)
        val mountain = repositoryPlace.getPlaceNearMountainList(currentRegion)
        val historicalBuilding = repositoryPlace.getPlaceHistoricalBuildingList(currentRegion)
        val shrine = repositoryPlace.getPlaceShrineList(currentRegion)
        val reserve = repositoryPlace.getPlaceReserveList(currentRegion)
        return PlaceListStateWithType.Success(
            data = PlaceLists(
                nearRiver = nearRiver.getPlacesData(),
                landscape = landscape.getPlacesData(),
                nearMountain = mountain.getPlacesData(),
                historicalBuilding = historicalBuilding.getPlacesData(),
                shrine = shrine.getPlacesData(),
                reserve = reserve.getPlacesData()
            )
        )
    }
}