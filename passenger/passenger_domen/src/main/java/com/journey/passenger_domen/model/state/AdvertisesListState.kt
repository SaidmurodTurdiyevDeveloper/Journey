package com.journey.passenger_domen.model.state

import com.journey.passenger_domen.model.ImageAdvertise
import com.journey.passenger_domen.model.ImagesAdvertise
import com.journey.passenger_domen.model.PlaceLists

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
sealed class AdvertisesListState {
    data class Error(val message: String) : AdvertisesListState()
    data class Success(
        val singleImageAdvertiseList: List<ImageAdvertise>,
        val multipleImagesAdvertiseList: List<ImagesAdvertise>,
    ) : AdvertisesListState()
}
