package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun TopBar(
    title: String,
    backgroundColor: Color,
    contentColor: Color,
    elevation: Dp,
    navigationContent:(@Composable BoxScope.() -> Unit)? = null,
    additionActionContent: (@Composable BoxScope.() -> Unit)? = null,
) {
    Surface(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),
        color = backgroundColor,
        elevation = elevation,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize()
            ) {
                navigationContent?.invoke(this)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = title.toUpperCase(Locale.getDefault()),
                    textAlign = TextAlign.Center,
                )
            }
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize()
            ) {
                additionActionContent?.invoke(this)
            }
        }
    }
}