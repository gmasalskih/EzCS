package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppState

class GrenadesPracticeStrategy(
    override val appState: AppState,
    override val navEventEmitter: FlowCollector<TargetNavigation>,
    override val cs: CoroutineScope
) : AppStateStrategy() {

    override fun applyStrategy() = appState.copy(
        drawerGesturesEnabled = false,
        appTopBarState = AppState.AppTopBarState.AppTopBar(
            titleRes = R.string.app_top_bar_title_grenades_practice,
            appTopBarNavItem = AppState.AppTopBarNavItem(
                icon = Icons.Filled.KeyboardArrowLeft,
                onClick = { navigateTo(TargetNavigation.Back) }
            )
        ),
        appBottomBarState = AppState.AppBottomBarState.NoAppBottomBar
    )
}