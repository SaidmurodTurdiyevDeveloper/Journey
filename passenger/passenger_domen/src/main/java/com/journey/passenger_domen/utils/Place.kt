package com.journey.passenger_domen.utils

import com.journey.common_data.module.PlaceDTO
import com.journey.passenger_domen.model.PlaceData

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */

fun PlaceDTO.getPlaceData(): PlaceData {
    return PlaceData(
        id = id,
        picture = picture,
        name = name,
        info = info
    )
}

fun List<PlaceDTO>.getPlacesData(): List<PlaceData> {
    return map { data ->
        data.getPlaceData()
    }
}