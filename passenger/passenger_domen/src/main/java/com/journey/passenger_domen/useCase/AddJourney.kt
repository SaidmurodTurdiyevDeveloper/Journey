package com.journey.passenger_domen.useCase

import com.journey.passenger_data.source.local.room.entity.JourneyEntity
import com.journey.passenger_data.interfaces.JourneyRepository

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
class AddJourney(private val repository: JourneyRepository) {

    suspend operator fun invoke(placeId: Int): Boolean {
        return repository.addJourney(
            JourneyEntity(
                placeId = placeId,
                isGone = false,
                timeStamp = System.currentTimeMillis(),
                description = ""
            )
        )
    }
}