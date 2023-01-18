package com.journey.myjourney.domen.model

import androidx.annotation.DrawableRes

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
data class PlaceJourneyData(val id: Int, @DrawableRes val picture: Int, val name: String, var info: String,var isGone:Int)
