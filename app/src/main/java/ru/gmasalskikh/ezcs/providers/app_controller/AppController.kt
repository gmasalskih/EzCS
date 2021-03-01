package ru.gmasalskikh.ezcs.providers.app_controller

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

interface AppController {

    val appEventEmitter: FlowCollector<AppEvent>
    val appEventCollector: SharedFlow<AppEvent>

    sealed class AppEvent {
        data class NavigateTo(
            val targetNavigation: TargetNavigation
        ) : AppEvent()

        data class OnNavigateDestinationChanged(
            val navEvent: Navigator.NavEvent
        ) : AppEvent()
    }
}