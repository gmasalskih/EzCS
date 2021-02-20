package ru.gmasalskikh.ezcs.screens.app_screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath

interface AppStateHolder {
    val appState: AppState
    val stateChangeFromNavEventEmitter: FlowCollector<NavEvent>
    val viewEventEmitter: FlowCollector<ViewEvent>
    fun onViewCreate()
    fun onViewDestroy()

    @Composable
    fun SetComposableScope()

    sealed class ViewEvent {
        object OnBackClick : ViewEvent()
        object OnMenuClick : ViewEvent()
    }

    @Immutable
    data class NavEvent(
        val targetNavigationPath: TargetNavigationPath,
        val bundle: Bundle? = null
    )

}