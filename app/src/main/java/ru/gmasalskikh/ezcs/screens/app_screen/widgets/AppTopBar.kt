package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

@Composable
fun AppTopBar(
    appBarViewState: AppViewState.AppTopBarState
) {
    val theme: AppTheme = LocalAppTheme.current
    when (appBarViewState) {
        AppViewState.AppTopBarState.NoAppTopBar -> {
        }
        is AppViewState.AppTopBarState.AppTopBar -> {
            TopBar(
                title = stringResource(id = appBarViewState.titleRes),
                backgroundColor = theme.colors.primary,
                contentColor = theme.colors.onPrimary,
                elevation = theme.elevations.medium,
                navIcon = {
                    AppTopBarIcon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = appBarViewState.appTopBarNavItem.icon,
                        tintColor = theme.colors.onPrimary,
                        onClick = appBarViewState.appTopBarNavItem.onClick,
                    )
                },
                extraIcon = {
                    appBarViewState.appTopBarExtraItem?.let { appBarState ->
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