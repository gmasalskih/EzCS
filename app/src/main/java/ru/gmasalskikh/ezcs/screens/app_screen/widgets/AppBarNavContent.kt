package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.AppViewEvent.*
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBarState.AppBar.*

@Composable
fun AppBarNavContent(
    modifier: Modifier = Modifier,
    appBarNavContentType: AppBarNavContentType,
    contentColor: Color
) {
    val appViewEvent = AmbientAppStateHolder.current.appViewEventEmitter
    val scope = rememberCoroutineScope()
    when (appBarNavContentType) {
        AppBarNavContentType.MENU -> {
            AppBarNavContentIcon(
                modifier = modifier,
                imageVector = appBarNavContentType.image,
                tintColor = contentColor
            ) {
                scope.launch {
                    appViewEvent.emit(OnMenuClick)
                }
            }
        }
        AppBarNavContentType.ARROW_BACK -> {
            AppBarNavContentIcon(
                modifier = modifier,
                imageVector = appBarNavContentType.image,
                tintColor = contentColor
            ) {
                scope.launch {
                    appViewEvent.emit(OnBackClick)
                }
            }
        }
    }
}