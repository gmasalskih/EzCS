package ru.gmasalskikh.ezcs.providers.app_controller

import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

interface AppController {

    val appEventEmitter: FlowCollector<AppEvent>

    sealed class AppEvent {
        data class NavigateTo(
            val targetNavigation: TargetNavigation
        ) : AppEvent()
    }
}