package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ru.gmasalskikh.ezcs.R

@Composable
fun AppBackground(
    isBlur: Boolean = true,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(),
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight,
                painter = painterResource(
                    if (isBlur) R.drawable.background_blur else R.drawable.background
                ),
                alpha = if (isBlur) 0.8f else 1f,
                contentDescription = null
            )
            content()
        }
    }
}