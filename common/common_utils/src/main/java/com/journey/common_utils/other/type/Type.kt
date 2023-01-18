package com.journey.common_utils.other.type

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/31/2023 5:44 PM for My Journey.
 */
enum class Type : Parcelable {
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

    companion object CREATOR : Parcelable.Creator<Type> {
        override fun createFromParcel(parcel: Parcel): Type {
            return when (parcel.readInt()) {
                1 -> NEAR_RIVER
                2 -> NEAR_MOUNTAIN
                3 -> HISTORICAL_BUILDING
                4 -> LANDSCAPE
                5 -> SHRINE
                6 -> RESERVE
                else -> NEAR_RIVER
            }
        }

        override fun newArray(size: Int): Array<Type?> {
            return arrayOfNulls(size)
        }
    }
}