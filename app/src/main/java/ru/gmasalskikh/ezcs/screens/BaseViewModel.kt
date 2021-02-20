package ru.gmasalskikh.ezcs.screens

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenViewState

abstract class BaseViewModel<VS : ViewState>(
    private val defaultViewState: VS,
    initViewStateType: ViewStateType,
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<VS, SideEffect> {

    private var screenState: ViewState.ScreenState<VS> by mutableStateOf(
        ViewState.ScreenState(
            viewStateType = initViewStateType,
            viewState = defaultViewState
        )
    )

    protected val _viewEvent = MutableSharedFlow<ViewEvent>()
    val viewEventEmitter: FlowCollector<ViewEvent>
    get() = _viewEvent

    override val container: Container<VS, SideEffect> = container(
        initialState = defaultViewState,
        savedStateHandle = savedStateHandle
    )

    var viewState: VS
        get() = screenState.viewState
        protected set(value) {
            screenState = screenState.copy(viewState = value)
        }

    val viewStateType
        get() = screenState.viewStateType

    open fun setDefaultState() {
        screenState = screenState.copy(viewState = defaultViewState)
    }

    open fun navigateTo(targetNavigation: TargetNavigation) {
    }

    open fun onViewCreate() {
    }

    open fun onViewDestroy() {
    }
}