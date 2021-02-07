package ru.gmasalskikh.ezcs.screens.app_screen

import ru.gmasalskikh.ezcs.navigation.TargetNavigation

interface AppStateHolder {
    val appState: AppState
    fun setTargetNavigation(targetNavigation: TargetNavigation)
}