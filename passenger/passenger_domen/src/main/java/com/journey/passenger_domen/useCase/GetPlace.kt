package com.journey.passenger_domen.useCase

import com.journey.passenger_domen.model.Place
import com.journey.passenger_data.interfaces.PlaceRepository

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/23/2023.
 */
class GetPlace(private val repository: PlaceRepository) {
    suspend operator fun invoke(id:Int):Place{
        val data=repository.getPlaceById(id)
        return Place(
            picture = data.picture,
            name = data.name,
            info = data.info,
            imageList = data.imageList,
            mapImage = data.mapImage
        )
    }
}