package com.journey.passenger_presenter.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/28/2023 9:15 PM for My Journey.
 */
sealed class AdvertiseEvent {
    data class GetAdvertise(val id: Int) : AdvertiseEvent()
    object LoadList : AdvertiseEvent()
}
