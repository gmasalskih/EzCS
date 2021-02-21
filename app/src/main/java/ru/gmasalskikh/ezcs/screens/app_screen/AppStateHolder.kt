package ru.gmasalskikh.ezcs.screens.app_screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath

interface AppStateHolder {
    val appState: AppState
    val appStateChangeEmitter: FlowCollector<NavEvent>
    val appViewEventEmitter: FlowCollector<AppViewEvent>
    fun onViewCreate()
    fun onViewDestroy()

    @Composable
    fun SetComposableScope()

    sealed class AppViewEvent {
        object OnBackClick : AppViewEvent()
        object OnMenuClick : AppViewEvent()
    }

    @Immutable
    data class NavEvent(
        val targetNavigationPath: TargetNavigationPath,
        val bundle: Bundle? = null
    )

}