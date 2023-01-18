package com.journey.passenger_domen.model

import androidx.annotation.DrawableRes
import com.journey.common_data.module.RegionDTO

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/30/2023 6:14 PM for My Journey.
 */
data class RegionData(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)

fun RegionDTO.toRegion(): RegionData {
    return RegionData(
        image = image,
        title = title,
        description = description
    )
}
