package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.gmasalskikh.ezcs.screens.app_screen.AppState
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme

@Composable
fun AppBar() {
    val theme: AppTheme = AmbientAppTheme.current
    when (val appBarState = AmbientAppStateHolder.current.appState.appBarState) {
        AppState.AppBarState.AppBarGone -> {}
        is AppState.AppBarState.AppBar -> {
            TopAppBar(
                title = stringResource(id = appBarState.titleRes),
                backgroundColor = theme.colors.primary,
                contentColor = theme.colors.onPrimary,
                elevation = theme.elevations.medium,
                navigationContent = {
                    AppBarNavContent(
                        modifier = Modifier.align(Alignment.Center),
                        appBarNavContentType = appBarState.navContentType
                    )
                }
            )
        }
    }
}