package ru.gmasalskikh.ezcs.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

abstract class BaseViewModel<VS : ViewState, VE : ViewEvent> :
    ViewModel(), ContainerHost<VS, SideEffect> {

    protected var viewLifecycleJob = Job()

    protected open suspend fun onViewEvent(viewEvent: VE) {

    }

    private val _viewEvent = MutableSharedFlow<VE>()
    val viewEventEmitter: FlowCollector<VE>
        get() = _viewEvent

    open fun onViewCreate() {
        viewLifecycleJob = Job()
        viewModelScope.launch(viewLifecycleJob) {
            _viewEvent.collect { viewEvent ->
                onViewEvent(viewEvent)
            }
        }
    }

    fun setSideEffect(sideEffect: SideEffect) = intent {
        postSideEffect(sideEffect)
        reduce {
            state.apply { currentSideEffect = sideEffect }
        }
    }

    open fun onViewDestroy() {
        viewLifecycleJob.cancel()
    }
}