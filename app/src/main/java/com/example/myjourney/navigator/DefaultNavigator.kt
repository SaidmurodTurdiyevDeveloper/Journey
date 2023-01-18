package com.example.myjourney.navigator

import com.example.common_utils.Activities
import com.example.common_utils.Navigator
import com.example.driver_presenter.GoToDriverActivity
import com.example.passenger_presenter.GoToPassengerActivity

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/7/2023.
 */
class DefaultNavigator : Navigator.Provider {
    override fun getActivities(activities: Activities): Navigator {
        return when (activities) {
            Activities.PassengerActivity -> {
                GoToPassengerActivity
            }
            Activities.DriverActivity -> {
                GoToDriverActivity
            }
        }
    }
}