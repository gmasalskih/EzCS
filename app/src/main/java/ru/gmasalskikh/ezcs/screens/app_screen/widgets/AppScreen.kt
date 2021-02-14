package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import ru.gmasalskikh.ezcs.navigation.ComposeNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewEvent
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientScaffoldState

@KoinApiExtension
@Composable
fun AppScreen(
    isAppBackgroundBlur: Boolean = true,
    topBar: @Composable () -> Unit = emptyContent(),
    drawerGesturesEnabled: Boolean = true,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = {},
    bottomBar: @Composable () -> Unit = emptyContent(),
) {
    val theme: AppTheme = AmbientAppTheme.current
    val scaffoldState = AmbientScaffoldState.current
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
