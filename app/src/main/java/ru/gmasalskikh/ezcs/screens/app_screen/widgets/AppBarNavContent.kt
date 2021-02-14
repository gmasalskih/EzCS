package ru.gmasalskikh.ezcs.screens.app_screen.widgets

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewEvent.*
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBarState.AppBar.*

@Composable
fun AppBarNavContent(
    modifier: Modifier = Modifier,
    appBarNavContentType: AppBarNavContentType
) {
    val appViewEvent = AmbientAppStateHolder.current.appViewEvent
    val scope = rememberCoroutineScope()
    when (appBarNavContentType) {
        AppBarNavContentType.MENU -> {
            AppBarNavContentIcon(
                modifier = modifier,
                imageVector = appBarNavContentType.image
            ) {
                scope.launch {
                    appViewEvent.emit(OnMenuClick)
                }
            }
        }
        AppBarNavContentType.ARROW_BACK -> {
            AppBarNavContentIcon(
                modifier = modifier,
                imageVector = appBarNavContentType.image
            ) {
                scope.launch {
                    appViewEvent.emit(OnBackClick)
                }
            }
        }
    }
}