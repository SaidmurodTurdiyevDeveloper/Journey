package com.journey.myjourney.domen.repository

import com.journey.myjourney.data.model.AdvertiseData_Full
import com.journey.myjourney.domen.model.ImageAdvertise
import com.journey.myjourney.domen.model.ImagesAdvertise

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
interface AdvertiseRepository {
    suspend fun getSingleImageAdvertise(): List<ImageAdvertise>
    suspend fun getAdvertise(id: Int): AdvertiseData_Full
    suspend fun getMultipleImageAdvertise(): List<ImagesAdvertise>
}