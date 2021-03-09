package ru.gmasalskikh.ezcs.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

data class Paddings(
    val small: Dp = 5.dp,
    val medium: Dp = 10.dp,
    val large: Dp = 20.dp
)

data class Borders(
    val thin: BorderStroke = BorderStroke(
        width = 2.dp,
        color = EzCSColorPalette.primary
    ),
    val medium: BorderStroke = BorderStroke(
        width = 4.dp,
        color = EzCSColorPalette.primary
    ),
    val bold: BorderStroke = BorderStroke(
        width = 6.dp,
        color = EzCSColorPalette.primary
    )
)

data class Elevations(
    val small: Dp = 2.dp,
    val medium: Dp = 6.dp,
    val large: Dp = 10.dp
)

object EzCSTheme : AppTheme {
    override val colors: Colors = EzCSColorPalette
    override val typography: Typography = EzCSTypography
    override val shapes: Shapes = EzCSShapes
    override val borders: Borders = Borders()
    override val paddings: Paddings = Paddings()
    override val elevations: Elevations = Elevations()
}

@Composable
fun EzCSTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppTheme provides EzCSTheme) {
        MaterialTheme(
            colors = EzCSTheme.colors,
            typography = EzCSTheme.typography,
            shapes = EzCSTheme.shapes,
            content = content
        )
    }
}