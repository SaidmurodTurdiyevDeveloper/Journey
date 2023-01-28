package com.journey.passenger_domen.di

import com.journey.passenger_data.interfaces.AdvertiseRepository
import com.journey.passenger_data.interfaces.JourneyRepository
import com.journey.passenger_data.interfaces.PlaceRepository
import com.journey.passenger_data.interfaces.RegionRepository
import com.journey.passenger_domen.useCase.*
import com.journey.passenger_domen.useCase.model.AdvertiseUseCase
import com.journey.passenger_domen.useCase.model.JourneyUseCase
import com.journey.passenger_domen.useCase.model.PlaceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object PassengerUseCaseModule {

    @Provides
    @Singleton
    fun provideAdvertiseUseCase(
        repositoryAdvertise: AdvertiseRepository
    ): AdvertiseUseCase {
        return AdvertiseUseCase(
            getAdvertise = GetAdvertise(
                repository = repositoryAdvertise
            ),
            getAdvertisesList = GetAdvertisesList(
                repository = repositoryAdvertise
            )
        )
    }

    @Provides
    @Singleton
    fun provideJourneyUseCase(
        repositoryJourney: JourneyRepository,
        repositoryPlace: PlaceRepository
    ): JourneyUseCase {
        return JourneyUseCase(
            getJourneysList = GetJourneysList(
                repositoryJourney = repositoryJourney,
                repositoryPlace = repositoryPlace
            ),
            addJourney = AddJourney(
                repository = repositoryJourney
            ),
            updateJourney = UpdateJourney(
                repositoryJourney = repositoryJourney,
                repositoryPlace=repositoryPlace
            ),
            deleteJourney = DeleteJourney(
                repositoryJourney = repositoryJourney,
                repositoryPlace = repositoryPlace
            ),
            clearJourney = ClearJourney(
                repositoryJourney = repositoryJourney,
                repositoryPlace = repositoryPlace
            )
        )
    }

    @Provides
    @Singleton
    fun providePlaceUseCase(
        repositoryPlace: PlaceRepository,
        repositoryRegion: RegionRepository
    ): PlaceUseCase {
        return PlaceUseCase(
            getPlace = GetPlace(
                repository = repositoryPlace
            ),
            getPlacesList = GetPlacesList(
                repositoryPlace = repositoryPlace
            ),
            getPlacesListWithType = GetPlacesListWithType(
                repositoryPlace = repositoryPlace,
                repositoryRegion = repositoryRegion
            )
        )
    }
}