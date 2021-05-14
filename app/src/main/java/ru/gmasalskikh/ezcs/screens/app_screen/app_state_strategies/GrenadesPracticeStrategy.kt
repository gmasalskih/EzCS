package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class GrenadesPracticeStrategy(
    override val appViewState: AppViewState,
    override val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>,
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
                titleRes = AppViewState.StringResourceType.StringIdRes(res = R.string.app_top_bar_title_grenades_practice),
                appTopBarNavItem = AppViewState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = { navigateTo(TargetNavigation.Back) }
                )
            ),
            appBottomBarState = AppViewState.AppBottomBarState.AppBottomBar(
                listOf(
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_grenade_practice_type_of_grenade_smoke,
                        icon = R.drawable.icon_smoke,
                        route = TargetNavigation.GrenadePracticeSmoke().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.GrenadePracticeSmoke(
                                    navParams
                                )
                            )
                        }
                    ),
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_grenade_practice_type_of_grenade_molotov,
                        icon = R.drawable.icon_molotov,
                        route = TargetNavigation.GrenadePracticeMolotov().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.GrenadePracticeMolotov(
                                    navParams
                                )
                            )
                        }
                    ),
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_grenade_practice_type_of_grenade_flash,
                        icon = R.drawable.icon_flash,
                        route = TargetNavigation.GrenadePracticeFlash().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.GrenadePracticeFlash(
                                    navParams
                                )
                            )
                        }
                    ),
                )
            )
        )
    }
}