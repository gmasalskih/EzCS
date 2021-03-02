package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppTopBarIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    isEnable: Boolean = true,
    tintColor: Color,
    onClick: () -> Unit
) {
    Image(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .clickable(
                enabled = isEnable,
                onClick = onClick
            ),
        imageVector = imageVector,
        colorFilter = ColorFilter.tint(tintColor),
        contentDescription = null
    )
}