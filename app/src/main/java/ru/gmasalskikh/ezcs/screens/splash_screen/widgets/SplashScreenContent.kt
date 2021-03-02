package ru.gmasalskikh.ezcs.screens.splash_screen.widgets

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import ru.gmasalskikh.ezcs.ui.theme.fontSize12Sp

@Composable
fun SplashScreenContent(
    appDescription: String,
    appLogo: Bitmap,
    appDescriptionColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = appDescription,
            color = appDescriptionColor,
            fontSize = fontSize12Sp
        )
        CoilImage(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .align(Alignment.BottomCenter),
            data = appLogo,
            contentDescription = null,
            loading = {
                CircularProgressIndicator()
            }
        )
    }
}