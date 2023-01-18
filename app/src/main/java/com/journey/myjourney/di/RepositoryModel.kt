package com.journey.myjourney.di

import com.journey.myjourney.data.repository.AdvertiseRepositoryImpl
import com.journey.myjourney.data.repository.PlaceRepositoryImpl
import com.journey.myjourney.data.repository.RegionRepositoryImpl
import com.journey.myjourney.domen.repository.AdvertiseRepository
import com.journey.myjourney.domen.repository.PlaceRepository
import com.journey.myjourney.domen.repository.RegionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/30/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModel {

    @Binds
    fun bindsAdvertiseRepository(repositry: AdvertiseRepositoryImpl): AdvertiseRepository

    @Binds
    fun bindsPlaceRepository(repositry: PlaceRepositoryImpl): PlaceRepository

    @Binds
    fun bindsRegionRepository(repositry: RegionRepositoryImpl): RegionRepository
}