package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppBarNavContentIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            imageVector = imageVector,
            contentDescription = null
        )
    }
}