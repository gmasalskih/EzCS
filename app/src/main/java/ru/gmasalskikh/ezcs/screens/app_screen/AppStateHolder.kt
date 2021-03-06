package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

interface AppStateHolder {
    val appViewState: AppViewState
    fun onViewCreate()
    fun onViewDestroy()
    fun setScaffoldState(scaffoldState: ScaffoldState)

    sealed class AppViewEvent {
        data class NavigateTo(
            val targetNavigation: TargetNavigation
        ) : AppViewEvent()
    }
}