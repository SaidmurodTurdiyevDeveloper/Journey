package com.journey.myjourney.data.model

import androidx.annotation.DrawableRes

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 26/11/2022.
 */
data class AdvertiseData_Full(
    val id: Int,
    val title: String,
    val info: String,
    @DrawableRes val image: Int,
    val imageList: List<Int>
)
