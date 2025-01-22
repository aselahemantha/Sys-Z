package com.example.basesdk.di

import android.content.Context
import com.example.basesdk.data.repository.institute.ConfigurationRepositoryImpl
import com.example.basesdk.domain.repository.institute.ConfigurationRepository
import com.example.basesdk.domain.configurationservice.ConfigurationService
import com.example.basesdk.domain.configurationservice.LogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides @Singleton
    fun providesConfigurationRepository(
        @ApplicationContext applicationContext: Context,
        customization: String,
        configurationService: ConfigurationService,
        logService: LogService,
        context: Context,
    ): ConfigurationRepository =
        ConfigurationRepositoryImpl(
            applicationContext = applicationContext,
            customization = customization,
            configurationService = configurationService,
            logService = logService,
            context = context,
        )
}
