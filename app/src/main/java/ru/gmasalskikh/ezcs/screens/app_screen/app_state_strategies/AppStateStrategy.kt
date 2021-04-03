package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState
import java.lang.IllegalStateException

abstract class AppStateStrategy {

    protected abstract val appViewState: AppViewState
    protected open val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>? = null
    abstract fun applyStrategy(): AppViewState

    protected suspend fun navigateTo(targetNavigation: TargetNavigation) {
        appViewEventEmitter?.emit(
            AppStateHolder.AppViewEvent.NavigateTo(targetNavigation)
        )
    }

    protected fun getAppStateWithNewTopBarTitle(titleRes: AppViewState.StringResourceType) =
        (appViewState.appTopBarState as? AppViewState.AppTopBarState.AppTopBar)?.let { appTopBar ->
            appViewState.copy(
                appTopBarState = appTopBar.copy(
                    titleRes = titleRes
                )
            )
        } ?: throw IllegalStateException("Current screen no has AppTopBar")

}