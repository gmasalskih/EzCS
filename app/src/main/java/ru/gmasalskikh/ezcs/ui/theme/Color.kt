package ru.gmasalskikh.ezcs.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val orange = Color(0xFFFEB02A)
val gray = Color(0xFF4C4C4C)
val black = Color(0xFF000000)
val white = Color(0xFFFFFFFF)
val transparent = Color(0x98000000)


val EzCSColorPalette = Colors(
    primary = orange,
    primaryVariant = orange,
    secondary = gray,
    secondaryVariant = gray,
    background = black,
    surface = transparent,
    error = Color.Red,
    onPrimary = gray,
    onSecondary = orange,
    onBackground = white,
    onSurface = white,
    onError = white,
    isLight = false
)