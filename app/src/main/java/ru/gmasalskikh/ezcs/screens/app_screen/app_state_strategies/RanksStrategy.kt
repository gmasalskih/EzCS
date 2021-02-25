package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.navigation.NavOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppState

class RanksStrategy(
    override val appState: AppState,
    override val navEventEmitter: FlowCollector<TargetNavigation>,
    override val cs: CoroutineScope
) :AppStateStrategy() {

    override fun applyStrategy(): AppState {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigation.Ranks.navId, true)
                .build()
        )
        return appState.copy(
            drawerGesturesEnabled = false,
            appTopBarState = AppState.AppTopBarState.AppTopBar(
                titleRes = R.string.app_top_bar_title_ranks,
                appTopBarNavItem = AppState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = { navigateTo(TargetNavigation.Back) }
                )
            ),
            appBottomBarState = AppState.AppBottomBarState.AppBottomBar(
                listOf(
                    AppState.AppBottomBarItem(
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
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_wingman,
                        icon = R.drawable.icon_wingman,
                        route = TargetNavigation.RanksWingman().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksWingman(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_danger_zone,
                        icon = R.drawable.icon_danger_zone,
                        route = TargetNavigation.RanksDangerZone().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksDangerZone(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
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