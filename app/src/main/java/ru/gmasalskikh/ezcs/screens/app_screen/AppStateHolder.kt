package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

interface AppStateHolder {
    val appState: AppState
    val navEventEmitter: FlowCollector<TargetNavigation>
    val viewEventEmitter: FlowCollector<ViewEvent>
    fun onViewCreate()
    fun onViewDestroy()

    @Composable
    fun SetComposableScope()

    sealed class ViewEvent {
        object OnBackClick : ViewEvent()
        object OnMenuClick : ViewEvent()
    }

}