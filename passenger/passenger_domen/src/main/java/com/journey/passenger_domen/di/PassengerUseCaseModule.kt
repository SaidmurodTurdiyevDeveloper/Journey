package com.journey.passenger_domen.di

import com.journey.passenger_data.interfaces.AdvertiseRepository
import com.journey.passenger_data.interfaces.JourneyRepository
import com.journey.passenger_data.interfaces.PlaceRepository
import com.journey.passenger_data.interfaces.RegionRepository
import com.journey.passenger_domen.useCase.*
import com.journey.passenger_domen.useCase.model.AdvertiseUseCase
import com.journey.passenger_domen.useCase.model.JourneyUseCase
import com.journey.passenger_domen.useCase.model.PlaceUseCase
import com.journey.passenger_domen.useCase.model.RegionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/25/2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
object PassengerUseCaseModule {

    @Provides
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
                repositoryPlace = repositoryPlace
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
    fun providePlaceUseCase(
        repositoryPlace: PlaceRepository,
        repositoryRegion: RegionRepository
    ): PlaceUseCase {
        return PlaceUseCase(
            getPlace = GetPlace(
                repository = repositoryPlace
            ),
            getPlacesList = GetPlacesList(
                repositoryPlace = repositoryPlace,
                repositoryRegion = repositoryRegion
            ),
            getPlacesListWithType = GetPlacesListWithType(
                repositoryPlace = repositoryPlace,
                repositoryRegion = repositoryRegion
            )
        )
    }

    @Provides
    fun provideRegionUseCase(
        repositoryRegion: RegionRepository
    ): RegionUseCase {
        return RegionUseCase(
            getRegion = GetCurrentRegion(
                repositoryRegion = repositoryRegion
            ),
            setRegion = SetCurrentRegion(
                repositoryRegion = repositoryRegion
            )
        )
    }
}