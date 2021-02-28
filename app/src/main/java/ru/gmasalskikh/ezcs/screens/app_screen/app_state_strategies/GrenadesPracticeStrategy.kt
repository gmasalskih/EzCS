package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class GrenadesPracticeStrategy(
    override val appViewState: AppViewState,
    override val navEventEmitter: FlowCollector<TargetNavigation>,
    override val cs: CoroutineScope
) : AppStateStrategy() {

    override fun applyStrategy() = appViewState.copy(
        drawerGesturesEnabled = false,
        appTopBarState = AppViewState.AppTopBarState.AppTopBar(
            titleRes = R.string.app_top_bar_title_grenades_practice,
            appTopBarNavItem = AppViewState.AppTopBarNavItem(
                icon = Icons.Filled.KeyboardArrowLeft,
                onClick = { navigateTo(TargetNavigation.Back) }
            )
        ),
        appBottomBarState = AppViewState.AppBottomBarState.NoAppBottomBar
    )
}