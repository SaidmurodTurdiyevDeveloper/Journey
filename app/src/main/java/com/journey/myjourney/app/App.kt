package com.journey.myjourney.app

import android.app.Application
import com.journey.myjourney.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 15/11/2022.
 */
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}