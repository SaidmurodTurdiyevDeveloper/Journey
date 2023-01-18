package com.journey.myjourney.di

import com.journey.myjourney.domen.use_case.AdvertiseUseCase
import com.journey.myjourney.domen.use_case.PlaceUseCase
import com.journey.myjourney.domen.use_case.impl.AdvertiseUseCaseImpl
import com.journey.myjourney.domen.use_case.impl.PlaceUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 11/30/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModel {

    @Binds
    fun bindAdvertiseUseCase(useCase: AdvertiseUseCaseImpl): AdvertiseUseCase

    @Binds
    fun bindJourneyUseCase(useCase: PlaceUseCaseImpl): PlaceUseCase
}