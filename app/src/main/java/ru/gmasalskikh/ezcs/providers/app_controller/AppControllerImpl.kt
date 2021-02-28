package ru.gmasalskikh.ezcs.providers.app_controller

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope

class AppControllerImpl(
    private val cs: CustomCoroutineScope,
    private val navEventEmitter: FlowCollector<TargetNavigation>
) : AppController {

    private val _appEvent = MutableSharedFlow<AppController.AppEvent>()
    override val appEventEmitter: FlowCollector<AppController.AppEvent>
        get() = _appEvent

    init {
        cs.launch {
            subscribeToAppEvent()
        }
    }

    private suspend fun subscribeToAppEvent() {
        _appEvent.collect { event ->
            when (event) {
                is AppController.AppEvent.NavigateTo -> {
                    navEventEmitter.emit(event.targetNavigation)
                }
            }
        }
    }
}