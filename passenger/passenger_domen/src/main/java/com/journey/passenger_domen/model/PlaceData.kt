package com.journey.passenger_domen.model

import androidx.annotation.DrawableRes

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
data class PlaceData(
    val id: Int,
    @DrawableRes val picture: Int,
    val name: String,
    val info: String
)
