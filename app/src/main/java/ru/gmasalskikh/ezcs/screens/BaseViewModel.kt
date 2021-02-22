package ru.gmasalskikh.ezcs.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost

abstract class BaseViewModel<VS : ViewState, VE : ViewEvent> :
    ViewModel(), ContainerHost<VS, SideEffect> {

    protected var job = Job()

    protected open suspend fun onViewEvent(viewEvent: VE) {

    }

    private val _viewEvent = MutableSharedFlow<VE>()
    val viewEventEmitter: FlowCollector<VE>
        get() = _viewEvent

    open fun onViewCreate() {
        job = Job()
        viewModelScope.launch(job) {
            _viewEvent.collect { viewEvent ->
                onViewEvent(viewEvent)
            }
        }
    }

    open fun onViewDestroy() {
        job.cancel()
    }
}