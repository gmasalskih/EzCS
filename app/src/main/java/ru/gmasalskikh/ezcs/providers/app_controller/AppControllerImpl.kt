package ru.gmasalskikh.ezcs.providers.app_controller

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope

class AppControllerImpl(
    cs: CustomCoroutineScope,
    private val navEventEmitter: FlowCollector<TargetNavigation>,
    private val navEventFlow: Flow<Navigator.NavEvent>,
) : AppController {

    private val _appEvent = MutableSharedFlow<AppController.AppEvent>()
    override val appEventCollector: SharedFlow<AppController.AppEvent>
        get() = _appEvent.asSharedFlow()
    override val appEventEmitter: FlowCollector<AppController.AppEvent>
        get() = _appEvent

    init {
        cs.launch {
            subscribeToAppEvent()
        }
        cs.launch {
            subscribeToNavEvent()
        }
    }


    private suspend fun subscribeToAppEvent() {
        _appEvent.collect { event ->
            when (event) {
                is AppController.AppEvent.NavigateTo -> {
                    navEventEmitter.emit(event.targetNavigation)
                }
                else -> Unit
            }
        }
    }

    private suspend fun subscribeToNavEvent() {
        navEventFlow.collect { event ->
            _appEvent.emit(
                AppController.AppEvent.OnNavigateDestinationChanged(
                    event
                )
            )
        }
    }
}