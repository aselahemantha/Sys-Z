package com.example.uilibrary.common.datadog

import android.content.Context
import android.util.Log
import com.datadog.android.Datadog
import com.datadog.android.DatadogSite
import com.datadog.android.core.configuration.BatchSize
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.core.configuration.UploadFrequency
import com.datadog.android.event.EventMapper
import com.datadog.android.log.Logs
import com.datadog.android.log.LogsConfiguration
import com.datadog.android.ndk.NdkCrashReports
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.Rum
import com.datadog.android.rum.RumConfiguration
import com.datadog.android.rum.event.ViewEventMapper
import com.datadog.android.rum.model.ActionEvent
import com.datadog.android.rum.model.ErrorEvent
import com.datadog.android.rum.model.LongTaskEvent
import com.datadog.android.rum.model.ResourceEvent
import com.datadog.android.rum.model.ViewEvent
import com.datadog.android.sessionreplay.SessionReplay
import com.datadog.android.sessionreplay.SessionReplayConfiguration
import com.datadog.android.sessionreplay.material.MaterialExtensionSupport
import com.datadog.android.trace.Trace
import com.datadog.android.trace.TraceConfiguration
import timber.log.Timber

object GTNDataDog {
    private val tracedHosts =
        listOf(
            "datadoghq.com",
        )
    private const val SAMPLE_IN_ALL_SESSIONS = 100f

    internal const val ATTR_IS_MAPPED = "is_mapped"

    private const val CLIENT_TOKEN = "pubd10b0f8e796319b746d531a5730ec10e"

    private const val APPLICATION_ID = "4e50e086-bc66-4f65-aa6f-b324acd09315"

    private const val SITE_SIMPLE = "us1"
    private const val SITE_CAPITAL = "US1"

    fun initializeDatadog(localContext: Context) {
        Datadog.setVerbosity(Log.VERBOSE)
        Datadog.initialize(
            localContext,
            createDatadogConfiguration(),
            TrackingConsent.GRANTED,
        )

        val rumConfig = createRumConfiguration()
        Rum.enable(rumConfig)

        val logsConfig = LogsConfiguration.Builder().build()
        Logs.enable(logsConfig)

        val sessionReplayConfig =
            SessionReplayConfiguration.Builder(SAMPLE_IN_ALL_SESSIONS)
                .addExtensionSupport(MaterialExtensionSupport()).build()
        SessionReplay.enable(sessionReplayConfig)

        val tracesConfig = TraceConfiguration.Builder().build()
        Trace.enable(tracesConfig)

        NdkCrashReports.enable()
    }

    private fun createDatadogConfiguration(): Configuration {
        val configBuilder =
            Configuration.Builder(
                clientToken = CLIENT_TOKEN,
                env = "debug",
                variant = SITE_SIMPLE,
            ).setFirstPartyHosts(tracedHosts).setBatchSize(BatchSize.SMALL)
                .setUploadFrequency(UploadFrequency.FREQUENT)

        try {
            configBuilder.useSite(DatadogSite.valueOf(SITE_CAPITAL))
        } catch (e: IllegalArgumentException) {
            Timber.e("Error setting site to $SITE_CAPITAL")
        }

        return configBuilder.build()
    }

    private fun createRumConfiguration(): RumConfiguration {
        return RumConfiguration.Builder(APPLICATION_ID)
            .setTelemetrySampleRate(100f).trackUserInteractions().trackLongTasks(250L)
            .setViewEventMapper(
                object : ViewEventMapper {
                    override fun map(event: ViewEvent): ViewEvent {
                        event.context?.additionalProperties?.put(ATTR_IS_MAPPED, true)
                        return event
                    }
                },
            ).setActionEventMapper(
                object : EventMapper<ActionEvent> {
                    override fun map(event: ActionEvent): ActionEvent {
                        event.context?.additionalProperties?.put(ATTR_IS_MAPPED, true)
                        return event
                    }
                },
            ).setResourceEventMapper(
                object : EventMapper<ResourceEvent> {
                    override fun map(event: ResourceEvent): ResourceEvent {
                        event.context?.additionalProperties?.put(ATTR_IS_MAPPED, true)
                        return event
                    }
                },
            ).setErrorEventMapper(
                object : EventMapper<ErrorEvent> {
                    override fun map(event: ErrorEvent): ErrorEvent {
                        event.context?.additionalProperties?.put(ATTR_IS_MAPPED, true)
                        return event
                    }
                },
            ).setLongTaskEventMapper(
                object : EventMapper<LongTaskEvent> {
                    override fun map(event: LongTaskEvent): LongTaskEvent {
                        event.context?.additionalProperties?.put(ATTR_IS_MAPPED, true)
                        return event
                    }
                },
            ).build()
    }
}
