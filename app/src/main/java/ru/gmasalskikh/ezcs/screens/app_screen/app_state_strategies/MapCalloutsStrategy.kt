package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class MapCalloutsStrategy(
    override val appViewState: AppViewState,
    override val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>,
    override val cs: CoroutineScope
) : AppStateStrategy() {

    override fun applyStrategy() = appViewState.copy(
        drawerGesturesEnabled = false,
        appTopBarState = AppViewState.AppTopBarState.AppTopBar(
            titleRes = R.string.app_top_bar_title_map_callouts,
            appTopBarNavItem = AppViewState.AppTopBarNavItem(
                icon = Icons.Filled.KeyboardArrowLeft,
                onClick = { navigateTo(TargetNavigation.Back) }
            )
        )
    )
}