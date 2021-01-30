package ru.gmasalskikh.ezcs.screens.preview.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PreviewItem(
    border: BorderStroke,
    shape: CornerBasedShape,
    backgroundColor: Color,
    content: @Composable ()->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                // aspectRatio 9:16 = 0.5625f
                .aspectRatio(0.5625f)
                .fillMaxSize(),
            border = border,
            shape = shape,
            color = backgroundColor
        ) {
            content()
        }
    }
}