package ru.gmasalskikh.ezcs.screens

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

abstract class BaseViewModel<VS : ViewState>(
    private val defaultViewState: VS,
    initViewStateType: ViewStateType
) : ViewModel() {

    private var screenState: ViewState.ScreenState<VS> by mutableStateOf(
        ViewState.ScreenState(
            viewStateType = initViewStateType,
            viewState = defaultViewState
        )
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

    open fun showDate() {
        screenState = screenState.copy(viewStateType = ViewStateType.Data)
    }

    open fun showLoading() {
        screenState = screenState.copy(viewStateType = ViewStateType.Loading)
    }

    open fun showErr(err: Throwable) {
        screenState = screenState.copy(
            viewStateType = ViewStateType.Error(msgErr = err.message, err = err)
        )
    }

    open fun navigateTo(targetNavigation: TargetNavigation){
    }

    open fun onViewCreate() {
    }

    open fun onViewDestroy() {
    }
}