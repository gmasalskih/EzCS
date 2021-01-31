package ru.gmasalskikh.ezcs.screens.main_menu.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.accompanist.coil.CoilImage
import ru.gmasalskikh.ezcs.ui.theme.fontSize16Sp
import ru.gmasalskikh.ezcs.ui.theme.fontSize20Sp
import ru.gmasalskikh.ezcs.ui.theme.transparent
import java.util.*

@Composable
fun MainMenuItemContent(
    modifier: Modifier = Modifier,
    label: String,
    @DrawableRes backgroundRes: Int
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
    ) {
        CoilImage(
            modifier = Modifier.fillMaxSize(),
            data = backgroundRes,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            loading = {
                CircularProgressIndicator()
            }
        )
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black.copy(alpha = 0.3f)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp),
                    text = label.toUpperCase(Locale.getDefault()),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp
                )
            }
        }
    }
}