package com.example.basesdk.di

import android.content.Context
import com.example.basesdk.domain.repository.institute.ConfigurationRepository
import com.example.basesdk.domain.usecase.institute.InitialFetchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideApplicationContext(
        @ApplicationContext context: Context,
    ): Context = context

    @Provides @Singleton
    fun provideCustomizationString(): String = "clientconfig1"

    @Provides @Singleton
    fun provideInitialFetchUseCase(repository: ConfigurationRepository): InitialFetchUseCase = InitialFetchUseCase(repository)

}
