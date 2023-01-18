package com.journey.passenger_domen.model.state

import com.journey.passenger_domen.model.PlaceData
import com.journey.passenger_domen.model.RegionData

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/30/2023 6:15 PM for My Journey.
 */
sealed class RegionState{
 data class Error(val message: String) : RegionState()
 data class Success(val data: RegionData) : RegionState()
}
