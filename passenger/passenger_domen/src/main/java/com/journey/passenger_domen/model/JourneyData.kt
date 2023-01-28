package com.journey.passenger_domen.model

import androidx.annotation.DrawableRes

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
data class JourneyData(
    val id: Int,
    val place: PlaceData,
    val description: String,
    val timeStamp:Long,
    var isGone: TYPE_GONE
)

enum class TYPE_GONE {
    GO, Current, Gone
}
