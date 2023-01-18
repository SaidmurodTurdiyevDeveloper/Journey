package com.journey.myjourney.data.source.local.deafoult

import android.content.Context
import com.journey.myjourney.R
import com.journey.myjourney.data.model.PlaceData_Full
import com.journey.myjourney.data.model.TYPE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/30/2022.
 */
@Singleton
class PlaceList @Inject constructor(@ApplicationContext private var context: Context) {
    private var list = ArrayList<PlaceData_Full>()
    fun getList(): List<PlaceData_Full> {
        return list
    }

    init {
        repeat(20) {
            list.add(
                PlaceData_Full(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon)+" ${list.size}",
                    context.getString(R.string.text_registon_description)+"Near river",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    TYPE.NEAR_RIVER
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceData_Full(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon)+" ${list.size}",
                    context.getString(R.string.text_registon_description)+" Historical Building",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    TYPE.HISTORICAL_BUILDING
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceData_Full(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon)+" ${list.size}",
                    context.getString(R.string.text_registon_description)+" Near Mountain",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    TYPE.NEAR_MOUNTAIN
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceData_Full(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon)+" ${list.size}",
                    context.getString(R.string.text_registon_description)+" Reserve",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    TYPE.RESERVE
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceData_Full(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon)+" ${list.size}",
                    context.getString(R.string.text_registon_description)+" Landscape",
                    arrayListOf(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    TYPE.LANDSCAPE
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceData_Full(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon)+" ${list.size}",
                    context.getString(R.string.text_registon_description)+" Shrine",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    TYPE.SHRINE
                )
            )
        }
    }
}