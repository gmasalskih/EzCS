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

class RanksStrategy(
    override val appViewState: AppViewState,
    override val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>,
) : AppStateStrategy() {

    override fun applyStrategy(): AppViewState {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigation.Ranks.navId, true)
                .build()
        )
        return appViewState.copy(
            drawerGesturesEnabled = false,
            appTopBarState = AppViewState.AppTopBarState.AppTopBar(
                titleRes = AppViewState.StringResourceType.StringIdRes(R.string.app_top_bar_title_ranks),
                appTopBarNavItem = AppViewState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = { navigateTo(TargetNavigation.Back) }
                )
            ),
            appBottomBarState = AppViewState.AppBottomBarState.AppBottomBar(
                listOf(
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_competitive,
                        icon = R.drawable.icon_competitive,
                        route = TargetNavigation.RanksCompetitive().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksCompetitive(
                                    navParams
                                )
                            )
                        }
                    ),
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_wingman,
                        icon = R.drawable.icon_wingman,
                        route = TargetNavigation.RanksWingman().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksWingman(navParams)
                            )
                        }
                    ),
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_danger_zone,
                        icon = R.drawable.icon_danger_zone,
                        route = TargetNavigation.RanksDangerZone().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksDangerZone(navParams)
                            )
                        }
                    ),
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_profile_rank,
                        icon = R.drawable.icon_profile_rank,
                        route = TargetNavigation.RanksProfileRank().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksProfileRank(navParams)
                            )
                        }
                    )
                )
            )
        )
    }
}