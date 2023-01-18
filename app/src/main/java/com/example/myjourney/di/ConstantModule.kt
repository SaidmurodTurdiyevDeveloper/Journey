package com.example.myjourney.di

import com.example.common_utils.Navigator
import com.example.myjourney.navigator.DefaultNavigator
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