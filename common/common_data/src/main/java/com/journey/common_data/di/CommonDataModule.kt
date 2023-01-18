package com.journey.common_data.di

import android.content.Context
import com.journey.common_data.source.local.default.AdvertiseList
import com.journey.common_data.source.local.default.DefaultLocalStore
import com.journey.common_data.source.local.default.PlaceList
import com.journey.common_data.source.local.default.RegionList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/7/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
object CommonDataModule {

    @Provides
    @Singleton
    fun providesDefaultLocalStore(
        @ApplicationContext
        context: Context
    ): DefaultLocalStore {
        return DefaultLocalStore(
            advertiseList = AdvertiseList(context),
            placeList = PlaceList(context),
            regionList = RegionList(context)
        )
    }
}