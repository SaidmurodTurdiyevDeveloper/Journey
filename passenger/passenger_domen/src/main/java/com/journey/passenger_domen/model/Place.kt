package com.journey.passenger_domen.model

import androidx.annotation.DrawableRes
import com.journey.common_data.module.TYPE

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/23/2023.
 */
data class Place(
    @DrawableRes val picture: Int,
    val name: String,
    val info: String,
    val imageList: List<Int>,
    @DrawableRes val mapImage: Int)


