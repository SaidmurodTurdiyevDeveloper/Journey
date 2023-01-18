package com.journey.myjourney.domen.use_case.impl

import com.journey.myjourney.data.model.AdvertiseData_Full
import com.journey.myjourney.domen.model.ImageAdvertise
import com.journey.myjourney.domen.model.ImagesAdvertise
import com.journey.myjourney.domen.repository.AdvertiseRepository
import com.journey.myjourney.domen.use_case.AdvertiseUseCase
import com.journey.myjourney.other.ResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
class AdvertiseUseCaseImpl @Inject constructor(private var repository: AdvertiseRepository) : AdvertiseUseCase {
    override fun getMainAdvertise(): Flow<ResponseData<List<ImageAdvertise>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repository.getSingleImageAdvertise()
            emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun getAdditionalAdvertise(): Flow<ResponseData<List<ImagesAdvertise>>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val list = repository.getMultipleImageAdvertise()
            emit(ResponseData.Success(list))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }

    override fun getAdvertiseById(id: Int): Flow<ResponseData<AdvertiseData_Full>> = flow {
        emit(ResponseData.Loading(true))
        try {
            val data = repository.getAdvertise(id)
            emit(ResponseData.Success(data))
        } catch (e: Exception) {
            emit(ResponseData.Error(e.message.toString()))
        }
    }
}