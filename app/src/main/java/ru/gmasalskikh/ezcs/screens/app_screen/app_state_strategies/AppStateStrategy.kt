package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState
import java.lang.IllegalStateException

abstract class AppStateStrategy {

    protected abstract val appViewState: AppViewState
    protected open val cs: CoroutineScope? = null
    protected open val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>? = null
    abstract fun applyStrategy(): AppViewState

    protected fun navigateTo(targetNavigation: TargetNavigation) = cs?.launch {
        appViewEventEmitter?.emit(
            AppStateHolder.AppViewEvent.NavigateTo(targetNavigation)
        )
    }

    protected fun getAppStateWithNewTopBarTitle(@StringRes titleRes: Int) =
        (appViewState.appTopBarState as? AppViewState.AppTopBarState.AppTopBar)?.let { appTopBar ->
            appViewState.copy(
                appTopBarState = appTopBar.copy(
                    titleRes = titleRes
                )
            )
        } ?: throw IllegalStateException("Current screen no has AppTopBar")

}