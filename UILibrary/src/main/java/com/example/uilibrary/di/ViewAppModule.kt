package com.example.uilibrary.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewAppModule {
//    @Provides
//    fun provideNavigationViewModel(): NavigationViewModel = NavigationViewModel() // Example dependency
}
