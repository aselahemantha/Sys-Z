package com.example.uilibrary.util.enums

enum class AppTheme(
    val code: String = "LIGHT",
) {
    LIGHT("LIGHT"),
    DARK("DARK"),
    SYSTEM("SYSTEM"),
    ;

    companion object {
        fun fromCode(code: String): AppTheme = values().find { it.code.lowercase() == code.lowercase() } ?: SYSTEM

        fun default(): AppTheme = SYSTEM
    }
}
