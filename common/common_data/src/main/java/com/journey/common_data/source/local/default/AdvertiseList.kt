package com.journey.common_data.source.local.default

import android.content.Context
import com.journey.common_data.R
import com.journey.common_data.module.AdvertiseDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/30/2022.
 */
class AdvertiseList @Inject constructor(@ApplicationContext private var context: Context) {
    private var list = ArrayList<AdvertiseDTO>()
    fun getList(): List<AdvertiseDTO> {
        return list
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