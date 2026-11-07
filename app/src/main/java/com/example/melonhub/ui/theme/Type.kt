package com.example.melonhub.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.melonhub.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

@OptIn(ExperimentalTextApi::class)
val PixelFont = FontFamily(
    Font(
        R.font.pixelify_sans,
        weight = FontWeight.Normal,
        variationSettings = FontVariation.Settings(FontVariation.weight(400))
    ),
    Font(
        R.font.pixelify_sans,
        weight = FontWeight.Medium,
        variationSettings = FontVariation.Settings(FontVariation.weight(500))
    ),
    Font(
        R.font.pixelify_sans,
        weight = FontWeight.SemiBold,
        variationSettings = FontVariation.Settings(FontVariation.weight(600))
    ),
    Font(
        R.font.pixelify_sans,
        weight = FontWeight.Bold,
        variationSettings = FontVariation.Settings(FontVariation.weight(700))
    )
)

val TipografiaRetro: Typography = Typography().conFuente(PixelFont)

private fun Typography.conFuente(fuente: FontFamily) = copy(
    displayLarge = displayLarge.copy(fontFamily = fuente),
    displayMedium = displayMedium.copy(fontFamily = fuente),
    displaySmall = displaySmall.copy(fontFamily = fuente),
    headlineLarge = headlineLarge.copy(fontFamily = fuente),
    headlineMedium = headlineMedium.copy(fontFamily = fuente),
    headlineSmall = headlineSmall.copy(fontFamily = fuente),
    titleLarge = titleLarge.copy(fontFamily = fuente),
    titleMedium = titleMedium.copy(fontFamily = fuente),
    titleSmall = titleSmall.copy(fontFamily = fuente),
    bodyLarge = bodyLarge.copy(fontFamily = fuente),
    bodyMedium = bodyMedium.copy(fontFamily = fuente),
    bodySmall = bodySmall.copy(fontFamily = fuente),
    labelLarge = labelLarge.copy(fontFamily = fuente),
    labelMedium = labelMedium.copy(fontFamily = fuente),
    labelSmall = labelSmall.copy(fontFamily = fuente)
)
