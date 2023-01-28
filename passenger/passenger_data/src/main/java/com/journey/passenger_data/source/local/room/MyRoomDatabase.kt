package com.journey.passenger_data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.journey.passenger_data.source.local.room.dao.JourneyDao
import com.journey.passenger_data.source.local.room.entity.JourneyEntity

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
@Database(entities = [JourneyEntity::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun getJourneyDao(): JourneyDao
}