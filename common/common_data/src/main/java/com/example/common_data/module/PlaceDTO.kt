package com.example.common_data.module

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/7/2023.
 */
data class PlaceDTO(val id: Int,
                    @DrawableRes val picture: Int,
                    val name: String,
                    val info: String,
                    val imageList: List<Int>,
                    @DrawableRes val mapImage: Int,
                    val regionId: Int,
                    val type_place: TYPE,
                    var isCanAdd: Boolean = true)

enum class TYPE() : Parcelable {
    NEAR_RIVER, NEAR_MOUNTAIN, HISTORICAL_BUILDING, LANDSCAPE, SHRINE, RESERVE;

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        when (this) {
            NEAR_RIVER -> parcel.writeInt(1)
            NEAR_MOUNTAIN -> parcel.writeInt(2)
            HISTORICAL_BUILDING -> parcel.writeInt(3)
            LANDSCAPE -> parcel.writeInt(4)
            SHRINE -> parcel.writeInt(5)
            RESERVE -> parcel.writeInt(6)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TYPE> {
        override fun createFromParcel(parcel: Parcel): TYPE {
            val index = parcel.readInt()
            return when (index) {
                1 -> NEAR_RIVER
                2 -> NEAR_MOUNTAIN
                3 -> HISTORICAL_BUILDING
                4 -> LANDSCAPE
                5 -> SHRINE
                6 -> RESERVE
                else -> NEAR_RIVER
            }
        }

        override fun newArray(size: Int): Array<TYPE?> {
            return arrayOfNulls(size)
        }
    }
}
