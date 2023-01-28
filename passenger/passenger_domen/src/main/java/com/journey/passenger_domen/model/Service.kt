package com.journey.passenger_domen.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/26/2023 7:46 PM for My Journey.
 */
data class Service(
    val id: Int,
    var name: String,
    val subService: List<SubService>
)

data class SubService(
    val id: Int,
    val name: String
)
