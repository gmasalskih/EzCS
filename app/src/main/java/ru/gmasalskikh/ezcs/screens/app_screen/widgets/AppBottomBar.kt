package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import java.util.*

@Composable
fun AppBottomBar() {
    val theme: AppTheme = AmbientAppTheme.current
    val navController = AmbientNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
    when (val appBarState = AmbientAppStateHolder.current.appState.appBottomBarState) {
        is AppState.AppBottomBarState.NoAppBottomBar -> Unit
        is AppState.AppBottomBarState.AppBottomBar -> {
            BottomBar(
                backgroundColor = theme.colors.primary
            ) {
                appBarState.listAppBottomBarItem.forEach { item ->
                    val isActive = currentRoute == item.route
                    val contentColor = if (isActive) theme.colors.onPrimary
                    else theme.colors.onPrimary.copy(alpha = 0.4f)
                    BottomBarItem(
                        modifier = Modifier.weight(1f),
                        label = stringResource(id = item.label).toUpperCase(Locale.getDefault()),
                        icon = item.icon?.let { vectorResource(id = it) },
                        contentColor = contentColor,
                        onClick = if (!isActive) item.onClick else null
                    )
                }
            }
        }
    }
}

/*
    activeContentColor: Color,
    inactiveContentColor: Color,
 */