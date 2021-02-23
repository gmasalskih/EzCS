package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.gmasalskikh.ezcs.screens.app_screen.AppState
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme

@Composable
fun AppTopBar() {
    val theme: AppTheme = AmbientAppTheme.current
    when (val appBarState = AmbientAppStateHolder.current.appState.appTopBarState) {
        AppState.AppTopBarState.NoAppTopBar -> {
        }
        is AppState.AppTopBarState.AppTopBar -> {
            TopBar(
                title = stringResource(id = appBarState.titleRes),
                backgroundColor = theme.colors.primary,
                contentColor = theme.colors.onPrimary,
                elevation = theme.elevations.medium,
                navIcon = {
                    AppTopBarIcon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = appBarState.appTopBarNavItem.icon,
                        tintColor = theme.colors.onPrimary,
                        onClick = appBarState.appTopBarNavItem.onClick,
                    )
                },
                extraIcon = {
                    appBarState.appTopBarExtraItem?.let { appBarState ->
                        val tintColor = if (appBarState.isEnable) theme.colors.onPrimary
                        else theme.colors.onPrimary.copy(alpha = 0.4f)
                        AppTopBarIcon(
                            modifier = Modifier.align(Alignment.Center),
                            imageVector = appBarState.icon,
                            tintColor = tintColor,
                            isEnable = appBarState.isEnable,
                            onClick = appBarState.onClick
                        )
                    }
                }
            )
        }
    }
}