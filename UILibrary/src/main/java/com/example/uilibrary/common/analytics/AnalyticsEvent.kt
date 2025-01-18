/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.uilibrary.common.analytics

import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Represents an analytics event.
 *
 * @param type - the event type. Wherever possible use one of the standard
 * event `Types`, however, if there is no suitable event type already defined, a custom event can be
 * defined as long as it is configured in your backend analytics system (for example, by creating a
 * Firebase Analytics custom event).
 *
 * @param extras - list of parameters which supply additional context to the event. See `Param`.
 */
data class AnalyticsEvent(
    val type: String,
    val extras: List<Param> = emptyList(),
) {
    // Standard analytics types.
    object Types {
        const val SCREEN_VIEW = FirebaseAnalytics.Param.SCREEN_CLASS // (extras: SCREEN_NAME)
        const val SELECT_ITEM = FirebaseAnalytics.Event.SELECT_ITEM
        const val BUTTON_CLICK = FirebaseAnalytics.Event.SELECT_CONTENT
        const val SUBMIT_RATING = "submit_rating"
    }

    /**
     * A key-value pair used to supply extra context to an
     * analytics event.
     *
     * @param key - the parameter key. Wherever possible use
     * one of the standard `ParamKeys`, however, if no suitable
     * key is available you can define your own as long as it is
     * configured in your backend analytics system (for example,
     * by creating a Firebase Analytics custom parameter).
     *
     * @param value - the parameter value.
     */
    data class Param(val key: String, val value: String)

    // Standard parameter keys.
    object ParamKeys {
        const val SCREEN_NAME = "screen_name"
        const val BUTTON_NAME = "button_name"
        const val ITEM_ID = "item_id"
        const val ITEM_NAME = "item_name"
        const val RATING_TYPE = "rating_type"
        const val RATING_CONTENT = "rating_content"
    }
}
