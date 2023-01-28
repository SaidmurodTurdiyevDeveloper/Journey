package com.journey.passenger_data.interfaces

import com.journey.common_data.module.AdvertiseDTO

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
interface AdvertiseRepository {
    suspend fun getSingleImageAdvertise(): List<AdvertiseDTO>
    suspend fun getAdvertise(id: Int): AdvertiseDTO
    suspend fun getMultipleImageAdvertise(): List<AdvertiseDTO>
}