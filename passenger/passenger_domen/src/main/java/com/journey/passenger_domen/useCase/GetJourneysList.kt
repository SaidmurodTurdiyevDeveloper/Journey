package com.journey.passenger_domen.useCase

import com.journey.passenger_domen.model.JourneyData
import com.journey.passenger_domen.model.state.JourneysListState
import com.journey.passenger_data.interfaces.JourneyRepository
import com.journey.passenger_data.interfaces.PlaceRepository
import com.journey.passenger_domen.model.TYPE_GONE
import com.journey.passenger_domen.utils.getPlaceData

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/24/2023.
 */
class GetJourneysList(
    private val repositoryJourney: JourneyRepository,
    private val repositoryPlace: PlaceRepository
) {

    suspend operator fun invoke(): JourneysListState {
        val list = getList().sortedWith(
            compareBy(JourneyData::isGone, JourneyData::timeStamp)
        )
        return if (list.isEmpty())
            JourneysListState.Error("you han not any journey")
        else
            JourneysListState.Success(
                list = list
            )
    }
    private suspend fun getList(): List<JourneyData> {
        val lsJourney = repositoryJourney.getJourneyList()
        val placeList = repositoryPlace.getPlaceAllList()
        val finalList = ArrayList<JourneyData>()
        val currentJourneyGo = repositoryJourney.getCurrentJourneyId()
        lsJourney.forEach { journey ->
            val place = placeList.firstOrNull { place ->
                place.id == journey.placeId
            }
            if (place != null) {
                finalList.add(
                    JourneyData(
                        id = journey.id ?: -1,
                        description = journey.description,
                        place = place.getPlaceData(),
                        timeStamp = journey.timeStamp,
                        isGone = if (journey.id == currentJourneyGo) TYPE_GONE.Current
                        else if (journey.isGone) TYPE_GONE.Gone
                        else TYPE_GONE.GO
                    )
                )
            }
        }
        return finalList
    }

}