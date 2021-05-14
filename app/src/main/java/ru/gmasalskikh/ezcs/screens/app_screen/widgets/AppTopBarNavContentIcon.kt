package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import kotlinx.coroutines.launch

@Composable
fun AppTopBarIcon(
    modifier: Modifier = Modifier,
    image: Painter,
    isEnable: Boolean = true,
    tintColor: Color,
    onClick: suspend () -> Unit
) {
    val cs = rememberCoroutineScope()
    Image(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .clickable(
                enabled = isEnable,
                onClick = { cs.launch { onClick() } }
            ),
        painter = image,
        colorFilter = ColorFilter.tint(tintColor),
        contentDescription = null
    )
}