package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import ru.gmasalskikh.ezcs.navigation.Navigator
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