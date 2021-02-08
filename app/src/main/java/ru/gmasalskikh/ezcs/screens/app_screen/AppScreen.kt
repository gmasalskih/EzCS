package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import org.koin.core.component.KoinApiExtension
import ru.gmasalskikh.ezcs.ui.common_widget.AppBackground
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.utils.AmbientScaffoldState

@KoinApiExtension
@Composable
fun AppScreen(
    isAppBackgroundBlur: Boolean = false,
    topBar: @Composable () -> Unit = emptyContent(),
    drawerGesturesEnabled: Boolean = true,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = {},
    bottomBar: @Composable () -> Unit = emptyContent(),
    content: @Composable BoxScope.() -> Unit = { },
) {
    val theme: AppTheme = AmbientAppTheme.current
    Providers(
        AmbientNavController provides rememberNavController(),
        AmbientScaffoldState provides rememberScaffoldState()
    ) {
        AppBackground(isAppBackgroundBlur) {
            Scaffold(
                scaffoldState = AmbientScaffoldState.current,
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
                        .fillMaxSize()
                        .padding(it),
                    content = content
                )
            }
        }
    }
}