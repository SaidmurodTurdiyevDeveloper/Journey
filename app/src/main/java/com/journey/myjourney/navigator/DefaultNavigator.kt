package com.journey.myjourney.navigator

import com.journey.common_utils.Activities
import com.journey.common_utils.Navigator
import com.journey.driver_presenter.GoToDriverActivity
import com.journey.passenger_presenter.GoToPassengerActivity

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/7/2023.
 */
class DefaultNavigator : Navigator.Provider {
    override fun getActivities(activities: com.journey.common_utils.Activities): Navigator {
        return when (activities) {
            com.journey.common_utils.Activities.PassengerActivity -> {
                GoToPassengerActivity
            }
            com.journey.common_utils.Activities.DriverActivity -> {
                GoToDriverActivity
            }
        }
    }
}