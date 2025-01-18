package com.gtn.uilibrary.coreui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.uilibrary.R

// Set of Material typography styles to start with

val Montserrat =
    FontFamily(
        listOf(
            Font(resId = R.font.montserrat_thin, FontWeight(250)),
            Font(resId = R.font.montserrat_extra_light, FontWeight(250)),
            Font(resId = R.font.montserrat_light, FontWeight.Light),
            Font(resId = R.font.montserrat_regular, FontWeight.Normal),
            Font(resId = R.font.montserrat_medium, FontWeight.Medium),
            Font(resId = R.font.montserrat_semi_bold, FontWeight.SemiBold),
            Font(resId = R.font.montserrat_bold, FontWeight.Bold),
            Font(resId = R.font.montserrat_extra_bold, FontWeight.ExtraBold),
            Font(resId = R.font.montserrat_black, FontWeight.Black),
        ),
    )

// Typography for Small screens (e.g., small phones)
val SmallTypography =
    Typography(
        displayLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 50.sp,
                // Reduced from 57.sp
            ),
        displayMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 38.sp,
                // Reduced from 45.sp
            ),
        displaySmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 30.sp,
                // Reduced from 36.sp
            ),
        headlineLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 28.sp,
                // Reduced from 32.sp
            ),
        headlineMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 24.sp,
                // Reduced from 28.sp
            ),
        headlineSmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 20.sp,
                // Reduced from 24.sp
            ),
        titleLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 20.sp,
                // Reduced from 22.sp
            ),
        titleMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 14.sp,
                // Reduced from 16.sp
            ),
        titleSmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 12.sp,
                // Reduced from 14.sp
            ),
        bodyLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 14.sp,
                // Reduced from 16.sp
            ),
        bodyMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 12.sp,
                // Reduced from 14.sp
            ),
        bodySmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 10.sp,
                // Reduced from 12.sp
            ),
        labelLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 12.sp,
                // Reduced from 14.sp
            ),
        labelMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 10.sp,
                // Reduced from 12.sp
            ),
        labelSmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 9.sp,
                // Reduced from 11.sp
            ),
    )

// Typography for Medium screens (e.g., regular phones)
val MediumTypography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 16.sp,
            ),
        bodyMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 14.sp,
            ),
        bodySmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 12.sp,
            ),
        displayLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 57.sp,
            ),
        displayMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 45.sp,
            ),
        displaySmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 36.sp,
            ),
        headlineLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 32.sp,
            ),
        headlineMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 28.sp,
            ),
        headlineSmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 24.sp,
            ),
        labelLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 14.sp,
            ),
        labelMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 12.sp,
            ),
        labelSmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 11.sp,
            ),
        titleLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 22.sp,
            ),
        titleMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 16.sp,
            ),
        titleSmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 14.sp,
            ),
    )

// Typography for Large screens (e.g., tablets or large phones)
val LargTypography =
    Typography(
        displayLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 64.sp,
                // Increased from 57.sp
            ),
        displayMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 50.sp,
                // Increased from 45.sp
            ),
        displaySmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 40.sp,
                // Increased from 36.sp
            ),
        headlineLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 36.sp,
                // Increased from 32.sp
            ),
        headlineMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 30.sp,
                // Increased from 28.sp
            ),
        headlineSmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 26.sp,
                // Increased from 24.sp
            ),
        titleLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 26.sp,
                // Increased from 22.sp
            ),
        titleMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 18.sp,
                // Increased from 16.sp
            ),
        titleSmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 16.sp,
                // Increased from 14.sp
            ),
        bodyLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 18.sp,
                // Increased from 16.sp
            ),
        bodyMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 16.sp,
                // Increased from 14.sp
            ),
        bodySmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 14.sp,
                // Increased from 12.sp
            ),
        labelLarge =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 16.sp,
                // Increased from 14.sp
            ),
        labelMedium =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 14.sp,
                // Increased from 12.sp
            ),
        labelSmall =
            TextStyle(
                fontFamily = Montserrat,
                fontSize = 12.sp,
                // Increased from 11.sp
            ),
    )
