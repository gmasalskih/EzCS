package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow

interface AppStateHolder {
    val appViewState: AppViewState
    val appViewEventEmitter: FlowCollector<AppViewEvent>
    val appViewEventCollector: SharedFlow<AppViewEvent>
    fun onViewCreate()
    fun onViewDestroy()
    fun setScaffoldState(scaffoldState: ScaffoldState)

    sealed class AppViewEvent {

    }



}