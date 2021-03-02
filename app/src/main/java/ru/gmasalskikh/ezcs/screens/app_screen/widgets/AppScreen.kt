package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.navigation.ComposeNavigation
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.LocalAppTheme
import ru.gmasalskikh.ezcs.utils.LocalScaffoldState

@Composable
fun AppScreen(
    isAppBackgroundBlur: Boolean = true,
    topBar: @Composable () -> Unit = {},
    drawerGesturesEnabled: Boolean = true,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = {},
    bottomBar: @Composable () -> Unit = {},
) {
    val theme: AppTheme = LocalAppTheme.current
    val scaffoldState = LocalScaffoldState.current
    AppBackground(isAppBackgroundBlur) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = topBar,
            drawerContent = drawerContent,
            drawerScrimColor = theme.colors.background.copy(alpha = 0.4f),
            drawerShape = RoundedCornerShape(0.dp),
            drawerBackgroundColor = Color.Transparent,
            drawerElevation = theme.elevations.medium,
            drawerGesturesEnabled = drawerGesturesEnabled,
            backgroundColor = Color.Transparent,
            bottomBar = bottomBar
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                ComposeNavigation()
            }
        }
    }
}
