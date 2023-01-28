package com.journey.passenger_presenter.model

import com.journey.passenger_domen.model.JourneyData
import com.journey.passenger_domen.model.TYPE_GONE

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/28/2023 8:23 PM for My Journey.
 */
sealed class JourneyEvent{
    data class DeleteJourney(val id:Int):JourneyEvent()
    data class UpdateJourney(val data:JourneyData,val type:TYPE_GONE):JourneyEvent()
    data class AddJourney(val placeId:Int):JourneyEvent()
    object GetJourneyList:JourneyEvent()
    object ClearJourney:JourneyEvent()
}
