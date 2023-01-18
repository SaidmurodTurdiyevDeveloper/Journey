package com.journey.myjourney.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
@Entity
data class JourneyEntity(@PrimaryKey(autoGenerate = true) val id: Int, val placeId: Int, var isGone: Int)
