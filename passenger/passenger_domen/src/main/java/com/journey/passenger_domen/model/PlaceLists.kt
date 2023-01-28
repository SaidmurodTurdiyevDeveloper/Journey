package com.journey.passenger_domen.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/24/2023.
 */
data class PlaceLists(
    val nearRiver: List<PlaceData> = emptyList(),
    val landscape: List<PlaceData> = emptyList(),
    val nearMountain: List<PlaceData> = emptyList(),
    val historicalBuilding: List<PlaceData> = emptyList(),
    val shrine: List<PlaceData> = emptyList(),
    val reserve: List<PlaceData> = emptyList()
)
