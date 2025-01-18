package com.example.basesdk.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.util.Date

class LogMe // Cross mark icon (❌) for exceptions
// Check mark icon (✅) for data messages
// Information icon (ℹ️) for other messages
(message: Any) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    // ANSI escape codes for terminal text colors
    // private val RESET = "\u001B[0m"
    private val redState = "\u001B[31;1m"
    private val greenState = "\u001B[32;1m"
    private val cyanState = "\u001B[36;1m"

    init {
        when (message) {
            is Throwable -> log("❌", redState, getExceptionDetails(message))
            // Cross mark icon (❌) for exceptions
            is String -> log("✅", greenState, message)
            // Check mark icon (✅) for data messages
            else -> log("ℹ️", cyanState, message)
            // Information icon (ℹ️) for other messages
        }
    }

    private fun generateTag(): String {
        val stackTraceElement = Thread.currentThread().stackTrace[4]
        val className = stackTraceElement.className.substringAfterLast('.')
        val methodName = stackTraceElement.methodName
        return "$className.$methodName"
    }

    private fun log(
        icon: String,
        colorCode: String,
        message: Any,
    ) {
        try {
//            if (BuildConfig.DEBUG) {
//                val timestamp = dateFormat.format(Date())
//                val stackTraceElement = Thread.currentThread().stackTrace[4]
//                val className = stackTraceElement.className
//                val methodName = stackTraceElement.methodName
//                val fileName = stackTraceElement.fileName
//                val lineNumber = stackTraceElement.lineNumber
//                val tag = generateTag()
//                val logMessageTitle = "LOGME: $timestamp $icon"
//                println(logMessageTitle)
//                val logMessageSubTitle = "[$tag] $className.$methodName($fileName:$lineNumber)"
//                println(logMessageSubTitle)
//                val logMessageBody = toJsonString(message)
//                println(logMessageBody)
//            }
        } catch (e: Exception) {
            // Handle exception that occurred during logging
            println("Error occurred while logging: ${e.message}")
        }
    }

    private fun toJsonString(message: Any): String {
        return when (message) {
            is String -> message // Directly return strings
            is Collection<*> -> gson.toJson(message) // Convert collections (lists, sets, etc.) to JSON
            is Array<*> -> gson.toJson(message) // Convert arrays to JSON
            else -> gson.toJson(message) // Convert objects (POJOs) to JSON
        }
    }

    private fun getExceptionDetails(exception: Throwable): String {
        return "${exception.javaClass.simpleName}: ${exception.message}\n${exception.stackTrace.joinToString("\n")}"
    }
}
