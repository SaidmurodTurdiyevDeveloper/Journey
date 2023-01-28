package com.journey.passenger_data.repository

import com.journey.common_data.module.AdvertiseDTO
import com.journey.common_data.source.local.default.DefaultAdvertiseList
import com.journey.passenger_data.interfaces.AdvertiseRepository

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/30/2022.
 */
class AdvertiseRepositoryImpl (private var defaultAdvertiseList: DefaultAdvertiseList) :
    AdvertiseRepository {
    override suspend fun getSingleImageAdvertise(): List<AdvertiseDTO> {
        return defaultAdvertiseList.getList().filter { data ->
            data.imageList.isEmpty()
        }
    }

    override suspend fun getAdvertise(id: Int): AdvertiseDTO {
        return defaultAdvertiseList.getList().first { data ->
            data.id == id
        }
    }

    override suspend fun getMultipleImageAdvertise(): List<AdvertiseDTO> {
        return defaultAdvertiseList.getList().filter { data ->
            data.imageList.isNotEmpty()
        }
    }

}