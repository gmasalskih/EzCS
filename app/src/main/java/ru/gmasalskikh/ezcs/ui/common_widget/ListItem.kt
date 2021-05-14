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

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    elevation: Dp,
    shape: CornerBasedShape,
    border: BorderStroke,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .clickable(onClick = onClick),
        color = backgroundColor,
        elevation = elevation,
        shape = shape,
        border = border
    ) {
        content()
    }
}