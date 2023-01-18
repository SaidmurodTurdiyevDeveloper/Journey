package com.journey.myjourney.di

import android.content.Context
import androidx.room.Room
import com.journey.myjourney.data.source.local.room.MyRoomDatabase
import com.journey.myjourney.data.source.local.room.dao.JourneyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): MyRoomDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            MyRoomDatabase::class.java,
            "MyRoomDatabaseJourneyApp"
        ).build()

    @Provides
    @Singleton
    fun providesJourneyDao(database: MyRoomDatabase): JourneyDao = database.getJourneyDao()
}