package com.journey.common_data.di

import android.content.Context
import com.journey.common_data.source.local.default.DefaultAdvertiseList
import com.journey.common_data.source.local.default.DefaultPlaceList
import com.journey.common_data.source.local.default.DefaultRegionList
import com.journey.common_data.source.local.shared.SharedDatabase
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
    fun provideSharedDatabase(@ApplicationContext context: Context): SharedDatabase =
        SharedDatabase(context)


}