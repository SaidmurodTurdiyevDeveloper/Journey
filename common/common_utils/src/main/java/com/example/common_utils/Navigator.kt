package com.example.common_utils

import android.app.Activity

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/7/2023.
 */
interface Navigator {
    fun navigate(activity:Activity)

    interface Provider{
        fun getActivities(activities: Activities):Navigator
    }
}