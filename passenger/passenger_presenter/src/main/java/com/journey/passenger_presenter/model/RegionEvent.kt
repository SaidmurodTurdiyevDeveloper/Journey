package com.journey.passenger_presenter.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/31/2023 2:59 PM for My Journey.
 */
sealed class RegionEvent {
    data class SetCurrentRegion(val id: Int) : RegionEvent()
    object LoadCurrentRegion : RegionEvent()
}
