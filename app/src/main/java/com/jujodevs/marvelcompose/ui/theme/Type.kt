package com.jujodevs.marvelcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.jujodevs.marvelcompose.R

val GrandStander = FontFamily(
    Font(R.font.grandstander_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.grandstander_blackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.grandstander_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.grandstander_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.grandstander_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.grandstander_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.grandstander_extralight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.grandstander_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.grandstander_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.grandstander_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.grandstander_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.grandstander_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.grandstander_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.grandstander_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.grandstander_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.grandstander_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.grandstander_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.grandstander_thinitalic, FontWeight.Thin, FontStyle.Italic),
)

// Set of Material typography styles to start with
val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = GrandStander),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = GrandStander),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = GrandStander),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = GrandStander),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = GrandStander),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = GrandStander),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = GrandStander),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = GrandStander),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = GrandStander),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = GrandStander),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = GrandStander),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = GrandStander),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = GrandStander),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = GrandStander),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = GrandStander),
)
