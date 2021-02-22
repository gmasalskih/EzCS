package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.AppViewEvent.*
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppTopBarState.AppTopBar.*

@Composable
fun AppTopBarNavContent(
    modifier: Modifier = Modifier,
    appTopBarNavContentType: AppTopBarNavContentType,
    contentColor: Color
) {
    val appViewEvent = AmbientAppStateHolder.current.appViewEventEmitter
    val scope = rememberCoroutineScope()
    when (appTopBarNavContentType) {
        AppTopBarNavContentType.MENU -> {
            AppTopBarNavContentIcon(
                modifier = modifier,
                imageVector = appTopBarNavContentType.image,
                tintColor = contentColor
            ) {
                scope.launch {
                    appViewEvent.emit(OnMenuClick)
                }
            }
        }
        AppTopBarNavContentType.ARROW_BACK -> {
            AppTopBarNavContentIcon(
                modifier = modifier,
                imageVector = appTopBarNavContentType.image,
                tintColor = contentColor
            ) {
                scope.launch {
                    appViewEvent.emit(OnBackClick)
                }
            }
        }
    }
}