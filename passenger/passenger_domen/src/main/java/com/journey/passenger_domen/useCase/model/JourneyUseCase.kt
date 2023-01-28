package com.journey.passenger_domen.useCase.model

import com.journey.passenger_domen.useCase.AddJourney
import com.journey.passenger_domen.useCase.ClearJourney
import com.journey.passenger_domen.useCase.DeleteJourney
import com.journey.passenger_domen.useCase.GetJourneysList
import com.journey.passenger_domen.useCase.UpdateJourney

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
data class JourneyUseCase(
    val getJourneysList: GetJourneysList,
    val addJourney: AddJourney,
    val updateJourney: UpdateJourney,
    val deleteJourney: DeleteJourney,
    val clearJourney: ClearJourney
)
