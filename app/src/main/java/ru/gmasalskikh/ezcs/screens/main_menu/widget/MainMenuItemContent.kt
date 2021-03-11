package ru.gmasalskikh.ezcs.screens.main_menu.widget

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.ui.theme.fontSize20Sp
import java.util.*

@Composable
fun MainMenuItemContent(
    modifier: Modifier = Modifier,
    @StringRes labelRes: Int,
    @DrawableRes backgroundRes: Int
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
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
                    text = stringResource(id = labelRes).toUpperCase(Locale.getDefault()),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = fontSize20Sp
                )
            }
        }
    }
}