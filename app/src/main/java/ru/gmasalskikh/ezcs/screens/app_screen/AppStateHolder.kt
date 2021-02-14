package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

interface AppStateHolder {
    val appState: AppState
    val navEvent: MutableStateFlow<TargetNavigation>
    val appViewEvent: MutableSharedFlow<AppViewEvent>
    fun setTargetNavigation(targetNavigation: TargetNavigation)
    suspend fun navigateTo(targetNavigation: TargetNavigation)
    fun onViewCreate()
    fun onViewDestroy()
    @Composable
    fun SetComposableScope()
}