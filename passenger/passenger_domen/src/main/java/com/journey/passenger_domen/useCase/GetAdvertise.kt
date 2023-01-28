package com.journey.passenger_domen.useCase

import com.journey.passenger_domen.model.Advertise
import com.journey.passenger_data.interfaces.AdvertiseRepository

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/23/2023.
 */
class GetAdvertise(private val repository: AdvertiseRepository) {
    suspend operator fun invoke(id: Int): Advertise {
        val advertise = repository.getAdvertise(id)
        val list = ArrayList<Int>()
        list.add(advertise.image)
        list.addAll(advertise.imageList)
        return Advertise(
            images = list,
            title = advertise.title,
            info = advertise.info
        )
    }
}