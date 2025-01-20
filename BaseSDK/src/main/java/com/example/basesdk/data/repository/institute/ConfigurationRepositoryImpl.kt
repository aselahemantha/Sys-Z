package com.example.basesdk.data.repository.institute

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.basesdk.domain.configurationservice.ConfigurationService
import com.gtn.basesdk.domain.configurationservice.LogService
import com.example.basesdk.domain.model.InstitutionConfig
import com.example.basesdk.domain.repository.institute.ConfigurationRepository
import com.example.basesdk.util.LogMe
import com.example.basesdk.util.Resource
import timber.log.Timber
import javax.inject.Inject

class ConfigurationRepositoryImpl
    @Inject
    constructor(
        private val configurationService: ConfigurationService,
        private val logService: LogService,
        private val applicationContext: Context,
        private val customization: String,
        private val context: Context,
    ) : ConfigurationRepository {
        override suspend fun initialFetchConfiguration(): Resource<InstitutionConfig?> {
            try {
//                val isSuccess = configurationService.fetchConfiguration()
//                if (!isSuccess) {
//                    logService.logNonFatalCrash(Throwable("Firebase fetch configuration service response failed"))
//                }
            } catch (e: Exception) {
                LogMe(e.message.toString())
            }
            return Resource.Success(
                Gson().fromJson(
                    context.assets.open("default_config.json").bufferedReader().use {
                        it.readText()
                    },
                    object : TypeToken<InstitutionConfig>() {}.type,
                ),
            )
        }

        override suspend fun fetchConfiguration(): Resource<InstitutionConfig?> =
            Resource.Success(
                Gson().fromJson(
                    context.assets.open("default_config.json").bufferedReader().use {
                        it.readText()
                    },
                    object : TypeToken<InstitutionConfig>() {}.type,
                ),
            )

        private fun getJson(resourceName: String): String =
            try {
                val outString =
                    applicationContext.assets.open(resourceName).bufferedReader().use {
                        it.readText()
                    }
                outString
            } catch (e: Exception) {
                Timber.tag("Error reading JSON").e(e.message.toString())
                ""
            }
    }
