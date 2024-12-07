package com.adityrajiv.profileshowcase.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.adityrajiv.profileshowcase.R

val Barlow = FontFamily(
    Font(R.font.barlow_regular, FontWeight.Normal),
    Font(R.font.barlow_thin, FontWeight.Thin),
    Font(R.font.barlow_bold, FontWeight.Bold),
    Font(R.font.barlow_semi_bold, FontWeight.SemiBold),
    Font(R.font.barlow_light, FontWeight.Light),
    Font(R.font.barlow_extra_light, FontWeight.ExtraLight),
)

val BarlowCondensed = FontFamily(
    Font(R.font.barlow_condensed_regular, FontWeight.Normal),
    Font(R.font.barlow_condensed_thin, FontWeight.Thin),
    Font(R.font.barlow_condensed_semi_bold, FontWeight.SemiBold),
    Font(R.font.barlow_condensedlight, FontWeight.Light),
    Font(R.font.barlow_condensed_extra_light, FontWeight.ExtraLight),
)

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = Barlow),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = Barlow),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = Barlow),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = Barlow),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = Barlow),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = Barlow),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = Barlow),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = Barlow),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = Barlow),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = Barlow),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = Barlow),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = Barlow),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = Barlow),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = Barlow),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = Barlow)
)