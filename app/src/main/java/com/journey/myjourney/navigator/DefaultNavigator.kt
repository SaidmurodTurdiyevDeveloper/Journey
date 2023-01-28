package com.journey.myjourney.navigator

import com.journey.common_utils.activity.Activities
import com.journey.common_utils.navigator.Navigator
import com.journey.driver_presenter.GoToDriverActivity
import com.journey.passenger_presenter.GoToPassengerActivity

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