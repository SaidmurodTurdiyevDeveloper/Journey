package com.journey.passenger_data.di

import android.content.Context
import androidx.room.Room
import com.journey.common_data.source.local.default.DefaultAdvertiseList
import com.journey.common_data.source.local.default.DefaultPlaceList
import com.journey.common_data.source.local.default.DefaultRegionList
import com.journey.common_data.source.local.shared.SharedDatabase
import com.journey.passenger_data.interfaces.AdvertiseRepository
import com.journey.passenger_data.interfaces.JourneyRepository
import com.journey.passenger_data.interfaces.PlaceRepository
import com.journey.passenger_data.interfaces.RegionRepository
import com.journey.passenger_data.repository.AdvertiseRepositoryImpl
import com.journey.passenger_data.repository.JourneyRepositoryImpl
import com.journey.passenger_data.repository.PlaceRepositoryImpl
import com.journey.passenger_data.repository.RegionRepositoryImpl
import com.journey.passenger_data.source.local.room.MyRoomDatabase
import com.journey.passenger_data.source.local.room.dao.JourneyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object PassengerDataModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MyRoomDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MyRoomDatabase::class.java,
            "MyRoomDatabaseJourneyApp"
        ).build()


    @Provides
    @Singleton
    fun providesJourneyDao(database: MyRoomDatabase): JourneyDao = database.getJourneyDao()

    @Provides
    @Singleton
    fun provideAdvertiseRepository(): AdvertiseRepository {
        return AdvertiseRepositoryImpl(
            defaultAdvertiseList = DefaultAdvertiseList()
        )
    }

    @Provides
    @Singleton
    fun provideJourneyRepository(
        @ApplicationContext context: Context,
        localStore: SharedDatabase,
        roomStore: JourneyDao
    ): JourneyRepository {
        return JourneyRepositoryImpl(
            localStore = localStore,
            roomStore = roomStore,
            defaultPlacesList = DefaultPlaceList(context)
        )
    }

    @Provides
    @Singleton
    fun providePlaceRepository(
        @ApplicationContext context: Context
    ): PlaceRepository {
        return PlaceRepositoryImpl(
            defaultPlaceList = DefaultPlaceList(context)
        )
    }

    @Provides
    @Singleton
    fun provideRegionRepository(
        @ApplicationContext context: Context,
        localStore: SharedDatabase
    ): RegionRepository {
        return RegionRepositoryImpl(
            localStore = localStore,
            defaultRegionList = DefaultRegionList(context)
        )
    }
}