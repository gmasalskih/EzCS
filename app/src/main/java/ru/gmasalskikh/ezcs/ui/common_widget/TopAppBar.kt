package ru.gmasalskikh.ezcs.ui.common_widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@Composable
fun TopAppBar(
    title: String,
    backgroundColor: Color,
    contentColor: Color,
    elevation: Dp,
    isNavigableBack: Boolean = AmbientNavController.current.previousBackStackEntry != null,
    onBackPressed: (() -> Unit)? = null,
    additionActionContent: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val navController = AmbientNavController.current
    Column(
        modifier = Modifier.fillMaxSize()
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
                    if (isNavigableBack) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(onClick = {
                                    if (onBackPressed != null) onBackPressed() else navController.popBackStack()
                                }),
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            colorFilter = ColorFilter.tint(contentColor),
                            contentDescription = null
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = title.toUpperCase(),
                        textAlign = TextAlign.Center,
                    )
                }
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxSize()
                ) {
                    additionActionContent?.invoke()
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            content()
        }
    }
}