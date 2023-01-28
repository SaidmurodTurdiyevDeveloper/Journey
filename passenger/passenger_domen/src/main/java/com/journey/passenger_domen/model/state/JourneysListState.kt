package com.journey.passenger_domen.model.state

import com.journey.passenger_domen.model.JourneyData

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
sealed class JourneysListState {
    data class Error(val message: String) : JourneysListState()
    data class Success(val list: List<JourneyData>) : JourneysListState()
}
