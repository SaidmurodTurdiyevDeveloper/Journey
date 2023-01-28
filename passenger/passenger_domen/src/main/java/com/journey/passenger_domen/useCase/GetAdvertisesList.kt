package com.journey.passenger_domen.useCase

import com.journey.passenger_domen.model.state.AdvertisesListState
import com.journey.passenger_data.interfaces.AdvertiseRepository
import com.journey.passenger_domen.model.ImageAdvertise
import com.journey.passenger_domen.model.ImagesAdvertise

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
class GetAdvertisesList(private val repository: AdvertiseRepository) {
    suspend operator fun invoke(): AdvertisesListState {
        val imageList = repository.getSingleImageAdvertise().map { data ->
            ImageAdvertise(
                id = data.id,
                image = data.image
            )
        }
        val imagesList = repository.getMultipleImageAdvertise().map { data ->
            ImagesAdvertise(
                id = data.id,
                image = data.image,
                images = data.imageList
            )
        }
        return AdvertisesListState.Success(
            singleImageAdvertiseList = imageList,
            multipleImagesAdvertiseList = imagesList
        )
    }
}