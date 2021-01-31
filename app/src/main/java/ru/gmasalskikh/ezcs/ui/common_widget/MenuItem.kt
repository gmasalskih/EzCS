package ru.gmasalskikh.ezcs.ui.common_widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MenuItem(
    backgroundColor: Color,
    elevation: Dp,
    shape: CornerBasedShape,
    border: BorderStroke,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Surface(
        modifier = Modifier
            .aspectRatio(2f)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = backgroundColor,
        elevation = elevation,
        shape = shape,
        border = border
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}