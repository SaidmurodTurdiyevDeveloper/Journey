package com.example.common_data.module

import androidx.annotation.DrawableRes

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/7/2023.
 */
data class RegionDTO(
    var id: Int,
    @DrawableRes var image: Int,
    var title: String,
    var description: String
)
