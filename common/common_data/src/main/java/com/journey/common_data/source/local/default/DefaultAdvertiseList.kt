package com.journey.common_data.source.local.default

import com.journey.common_data.R
import com.journey.common_data.module.AdvertiseDTO

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/30/2022.
 */
class DefaultAdvertiseList() {
    private var list = ArrayList<AdvertiseDTO>()
    fun getList(): List<AdvertiseDTO> {
        return ArrayList<AdvertiseDTO>(list)
    }

    init {
        repeat(4) {
            list.add(AdvertiseDTO(list.size, "advertise", "advertise", R.drawable.advertise, emptyList()))
        }
        repeat(4) {
            list.add(AdvertiseDTO(list.size, "advertise", "advertise", R.drawable.advertise, arrayListOf(R.drawable.advertise, R.drawable.advertise, R.drawable.advertise, R.drawable.advertise)))
        }
    }
}