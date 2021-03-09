package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.ui.theme.fontSize8Sp

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    label: String,
    contentColor: Color,
    onClick: (() -> Unit)?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                enabled = onClick != null,
                onClick = onClick ?: {}
            ).padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (icon != null) Image(
            modifier = Modifier.weight(1f),
            painter = icon,
            colorFilter = ColorFilter.tint(contentColor),
            contentDescription = null
        )
        Text(
            text = label,
            textAlign = TextAlign.Center,
            color = contentColor,
            fontSize = fontSize8Sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}