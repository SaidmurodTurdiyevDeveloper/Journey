package com.example.myjourney.data.source.local.deafoult

import android.content.Context
import com.example.myjourney.R
import com.example.myjourney.data.model.AdvertiseData_Full
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/30/2022.
 */
class AdvertiseList @Inject constructor(@ApplicationContext private var context: Context) {
    private var list = ArrayList<AdvertiseData_Full>()
    fun getList(): List<AdvertiseData_Full> {
        return list
    }

    init {
        repeat(4) {
            list.add(AdvertiseData_Full(list.size, "advertise", "advertise", R.drawable.advertise, emptyList()))
        }
        repeat(4) {
            list.add(AdvertiseData_Full(list.size, "advertise", "advertise", R.drawable.advertise, arrayListOf(R.drawable.advertise, R.drawable.advertise, R.drawable.advertise, R.drawable.advertise)))
        }
    }
}