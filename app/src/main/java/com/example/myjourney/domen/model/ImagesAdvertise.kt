package com.example.myjourney.domen.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/28/2022.
 */
data class ImagesAdvertise(
    val id: Int,
    @DrawableRes
    var image: Int,
    var images: List<Int>
) : Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(image)
        parcel.writeInt(images.size)
        parcel.writeIntArray(images.toIntArray())

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImagesAdvertise> {
        override fun createFromParcel(parcel: Parcel): ImagesAdvertise {
            val id = parcel.readInt()
            val image = parcel.readInt()
            val size = parcel.readInt()
            val ls = IntArray(size)
            parcel.readIntArray(ls)
            return ImagesAdvertise(id, image, ls.toList())
        }

        override fun newArray(size: Int): Array<ImagesAdvertise?> {
            return arrayOfNulls(size)
        }
    }
}
