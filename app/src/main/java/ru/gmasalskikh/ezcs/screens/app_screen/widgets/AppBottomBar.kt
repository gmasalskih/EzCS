package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@Composable
fun AppBottomBar() {
    val theme: AppTheme = AmbientAppTheme.current
    val navController = AmbientNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
    Log.d("---", "currentRoute $currentRoute")
    when (val appBarState = AmbientAppStateHolder.current.appState.appBottomBarState) {
        is AppState.AppBottomBarState.NoAppBottomBar -> Unit
        is AppState.AppBottomBarState.AppBottomBar -> {
            BottomBar(
                backgroundColor = theme.colors.primary
            ) {
                appBarState.listAppBottomBarItem.forEach { item ->
                    val isActive = currentRoute == item.route.name
                    val contentColor = if (isActive) theme.colors.onPrimary
                    else theme.colors.onPrimary.copy(alpha = 0.6f)
                    BottomBarItem(
                        label = stringResource(id = item.itemName),
                        icon = item.itemIcon?.let { vectorResource(id = it) },
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