package com.journey.myjourney.domen.use_case

import com.journey.myjourney.data.model.AdvertiseData_Full
import com.journey.myjourney.domen.model.ImageAdvertise
import com.journey.myjourney.domen.model.ImagesAdvertise
import com.journey.myjourney.other.ResponseData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
interface AdvertiseUseCase {
    fun getMainAdvertise(): Flow<ResponseData<List<ImageAdvertise>>>
    fun getAdditionalAdvertise(): Flow<ResponseData<List<ImagesAdvertise>>>
    fun getAdvertiseById(id: Int): Flow<ResponseData<AdvertiseData_Full>>
}