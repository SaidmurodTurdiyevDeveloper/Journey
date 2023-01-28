package com.journey.driver_data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
@Entity
data class JourneyEntity(
    val placeId: Int,
    var isGone: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null
)
