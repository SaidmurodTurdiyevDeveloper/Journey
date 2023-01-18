package com.journey.passenger_presenter.model

import com.journey.common_utils.other.type.Type
import com.journey.passenger_presenter.viewModel.ViewModelPlace

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
sealed class PlaceEvent{
    data class GetPlace(val id:Int):PlaceEvent()
    data class LoadAllPlaceWithType(val type:Type) :PlaceEvent()
    object LoadPlaceWithType :PlaceEvent()
    object LoadAllPlace :PlaceEvent()
}
