package ru.gmasalskikh.ezcs.screens.preview.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.ui.theme.orange

@Composable
fun Dots(
    modifier: Modifier = Modifier,
    amountDots: Int = 1,
    size: Dp = 10.dp,
    color: Color,
    accentColor: Color,
    indexSelectedDot: Int = 0
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        repeat(amountDots) {
            Dot(
                size = size,
                color = if (indexSelectedDot == it) accentColor else color
            )
        }
    }
}

@Composable
private fun Dot(
    size: Dp = 10.dp,
    color: Color,
) {
    Surface(
        modifier = Modifier.size(size = size),
        color = color,
        shape = CircleShape,
        content = {}
    )
}

@Preview(showBackground = true)
@Composable
fun P() {
    Dot(color = orange)
}