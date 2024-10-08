package com.journey.common_data.source.local.default

import android.content.Context
import com.journey.common_data.R
import com.journey.common_data.module.PlaceDTO
import com.journey.common_utils.other.type.Type
import kotlin.random.Random

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/30/2022.
 */

class DefaultPlaceList  (private val context: Context) {
    private var list = ArrayList<PlaceDTO>()
    fun getList(): List<PlaceDTO> {
        return ArrayList<PlaceDTO>(list)
    }

    init {
        repeat(20) {
            list.add(
                PlaceDTO(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon) + " ${list.size}",
                    context.getString(R.string.text_registon_description) + "Near river",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    Type.NEAR_RIVER
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceDTO(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon) + " ${list.size}",
                    context.getString(R.string.text_registon_description) + " Historical Building",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    Type.HISTORICAL_BUILDING
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceDTO(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon) + " ${list.size}",
                    context.getString(R.string.text_registon_description) + " Near Mountain",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    Type.NEAR_MOUNTAIN
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceDTO(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon) + " ${list.size}",
                    context.getString(R.string.text_registon_description) + " Reserve",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    Type.RESERVE
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceDTO(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon) + " ${list.size}",
                    context.getString(R.string.text_registon_description) + " Landscape",
                    arrayListOf(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    Type.LANDSCAPE
                )
            )
        }
        repeat(20) {
            list.add(
                PlaceDTO(
                    list.size,
                    R.drawable.registon,
                    context.getString(R.string.text_registon) + " ${list.size}",
                    context.getString(R.string.text_registon_description) + " Shrine",
                    arrayListOf<Int>(R.drawable.registon, R.drawable.tilla_kori, R.drawable.sherdor, R.drawable.mirzo_ulugbek),
                    R.drawable.registon,
                    Random.nextInt(12),
                    Type.SHRINE
                )
            )
        }
    }
}