package com.journey.common_data.module

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.journey.common_utils.other.type.Type

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/7/2023.
 */
data class PlaceDTO(
    val id: Int,
    @DrawableRes val picture: Int,
    val name: String,
    val info: String,
    val imageList: List<Int>,
    @DrawableRes val mapImage: Int,
    val regionId: Int,
    val type_place: Type,
    var isGone: Boolean? = null
)


