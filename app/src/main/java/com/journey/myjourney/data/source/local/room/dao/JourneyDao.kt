package com.journey.myjourney.data.source.local.room.dao

import androidx.room.*
import com.journey.myjourney.data.source.local.room.entity.JourneyEntity

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
@Dao
interface JourneyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: JourneyEntity): Long

    @Delete
    fun deleteJourney(data: JourneyEntity): Int

    @Delete
    fun deleteListJourney(ls: List<JourneyEntity>)

    @Update
    fun update(data: JourneyEntity): Int

    @Query("SELECT * FROM journeyentity")
    fun getList(): List<JourneyEntity>

    @Query("SELECT * FROM journeyentity WHERE id=:id LIMIT 1")
    fun getJourneyById(id: Int): JourneyEntity
}