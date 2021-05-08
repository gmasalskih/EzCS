package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class GrenadesPracticeStrategyTickrate(
    override val appViewState: AppViewState,
    override val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>
) : AppStateStrategy() {

    override fun applyStrategy(): AppViewState {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigation.GrenadesPractice.navId, false)
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
            appBottomBarState = AppViewState.AppBottomBarState.AppBottomBar(
                listOf(
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_grenades_practice_tickrate,
                        icon = R.drawable.icon_tickrate_64,
                        route = TargetNavigation.TickRate64().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.TickRate64(
                                    navParams
                                )
                            )
                        }
                    ),
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_grenades_practice_tickrate,
                        icon = R.drawable.icon_tickrate_128,
                        route = TargetNavigation.TickRate128().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.TickRate128(navParams)
                            )
                        }
                    )
                )
            )
        )
    }
}