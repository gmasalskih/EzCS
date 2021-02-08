package ru.gmasalskikh.ezcs.screens.app_screen

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

interface AppStateHolder {
    val appState: AppState
    val navigationEventBus: MutableStateFlow<TargetNavigation?>
    fun setTargetNavigation(targetNavigation: TargetNavigation)
    suspend fun navigateTo(targetNavigation: TargetNavigation)

    fun setNewAppState(appState: AppState)

    @Composable
    fun SetComposableScope()
}