package com.journey.common_utils

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/7/2023.
 */
sealed class Activities {
    object PassengerActivity : com.journey.common_utils.Activities()
    object DriverActivity : com.journey.common_utils.Activities()
}
