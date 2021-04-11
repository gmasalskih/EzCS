package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.LocalAppTheme
import ru.gmasalskikh.ezcs.utils.LocalNavController
import java.util.*

@Composable
fun AppBottomBar(
    appBottomBarViewState: AppViewState.AppBottomBarState
) {
    val theme: AppTheme = LocalAppTheme.current
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
    when (appBottomBarViewState) {
        is AppViewState.AppBottomBarState.NoAppBottomBar -> Unit
        is AppViewState.AppBottomBarState.AppBottomBar -> {
            BottomBar(
                backgroundColor = theme.colors.primary
            ) {
                appBottomBarViewState.listAppBottomBarItem.forEach { item ->
                    val isActive = currentRoute == item.route
                    val contentColor = if (isActive) theme.colors.onPrimary
                    else theme.colors.onPrimary.copy(alpha = 0.4f)
                    BottomBarItem(
                        modifier = Modifier.weight(1f),
                        label = stringResource(id = item.label).toUpperCase(Locale.getDefault()),
                        icon = item.icon?.let { painterResource(id = it) },
                        contentColor = contentColor,
                        onClick = if (!isActive) item.onClick else null
                    )
                }
            }
        }
    }
}