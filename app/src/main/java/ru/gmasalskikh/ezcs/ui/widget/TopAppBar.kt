package ru.gmasalskikh.ezcs.ui.widget

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.ui.theme.darkGray
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@Composable
fun TopAppBar(
    title: String,
    backgroundColor: Color,
    contentColor: Color,
    isNavigableBack: Boolean,
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
            elevation = 6.dp,
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
                                .clickable(onClick = navController::popBackStack),
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            colorFilter = ColorFilter.tint(darkGray)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Red
                    ) {

                    }
                }
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxSize()
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Green
                    ) {

                    }
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