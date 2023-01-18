package com.example.common_data.module

import androidx.annotation.DrawableRes

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/7/2023.
 */
data class AdvertiseDTO(
    val id: Int,
    val title: String,
    val info: String,
    @DrawableRes val image: Int,
    val imageList: List<Int>
)
