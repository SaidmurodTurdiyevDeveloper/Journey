package com.journey.myjourney.di

import com.journey.common_utils.Navigator
import com.journey.myjourney.navigator.DefaultNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/12/2022.
 */
@InstallIn(SingletonComponent::class)
@Module
object ConstantModule {
    @Provides
    @Singleton
    fun provideNavigateProvider(): Navigator.Provider {
        return DefaultNavigator()
    }
}