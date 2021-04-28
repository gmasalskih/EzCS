package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class WeaponCharacteristicsDetailsStrategy(
    override val appViewState: AppViewState,
    private val topAppBarTitle: String,
    override val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>
) : AppStateStrategy() {

    override fun applyStrategy() = appViewState.copy(
        drawerGesturesEnabled = false,
        appTopBarState = AppViewState.AppTopBarState.AppTopBar(
            titleRes = AppViewState.StringResourceType.StringNative(res = topAppBarTitle),
            appTopBarNavItem = AppViewState.AppTopBarNavItem(
                icon = Icons.Filled.KeyboardArrowLeft,
                onClick = { navigateTo(TargetNavigation.Back) }
            )
        ),
        appBottomBarState = AppViewState.AppBottomBarState.NoAppBottomBar
    )
}