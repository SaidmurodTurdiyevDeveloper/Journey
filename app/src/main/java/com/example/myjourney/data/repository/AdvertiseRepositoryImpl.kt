package com.example.myjourney.data.repository

import com.example.myjourney.data.model.AdvertiseData_Full
import com.example.myjourney.data.source.local.deafoult.AdvertiseList
import com.example.myjourney.domen.model.ImageAdvertise
import com.example.myjourney.domen.model.ImagesAdvertise
import com.example.myjourney.domen.repository.AdvertiseRepository
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/30/2022.
 */
class AdvertiseRepositoryImpl @Inject constructor(private var advertise: AdvertiseList) : AdvertiseRepository {
    override suspend fun getSingleImageAdvertise(): List<ImageAdvertise> {
        return advertise.getList().filter { data ->
            data.imageList.isEmpty()
        }.map { data ->
            ImageAdvertise(data.id, data.image)
        }
    }

    override suspend fun getAdvertise(id: Int): AdvertiseData_Full {
        return advertise.getList().first { data ->
            data.id == id
        }
    }

    override suspend fun getMultipleImageAdvertise(): List<ImagesAdvertise> {
        return advertise.getList().filter { data ->
            data.imageList.isNotEmpty()
        }.map { data ->
            ImagesAdvertise(data.id, data.image, data.imageList)
        }
    }

}