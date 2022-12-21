package com.example.myjourney.di

import com.example.myjourney.data.repository.AdvertiseRepositoryImpl
import com.example.myjourney.data.repository.PlaceRepositoryImpl
import com.example.myjourney.data.repository.RegionRepositoryImpl
import com.example.myjourney.domen.repository.AdvertiseRepository
import com.example.myjourney.domen.repository.PlaceRepository
import com.example.myjourney.domen.repository.RegionRepository
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