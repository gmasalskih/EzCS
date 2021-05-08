package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class GrenadesPracticeStrategyDetails(
    override val appViewState: AppViewState,
    override val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>
) : AppStateStrategy() {

    override fun applyStrategy(): AppViewState {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigation.GrenadePracticeDetails().navId, true)
                .build()
        )
        return appViewState.copy(
            drawerGesturesEnabled = false,
            appTopBarState = AppViewState.AppTopBarState.AppTopBar(
                titleRes = AppViewState.StringResourceType.StringIdRes(res = R.string.app_top_bar_title_grenades_practice_type_of_grenade_flash),
                appTopBarNavItem = AppViewState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = { navigateTo(TargetNavigation.Back) }
                )
            ),
            appBottomBarState = AppViewState.AppBottomBarState.NoAppBottomBar
        )
    }
}
