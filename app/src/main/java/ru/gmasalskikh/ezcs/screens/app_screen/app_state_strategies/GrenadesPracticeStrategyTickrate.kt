package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.FlowCollector
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState
import ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps.PlacesOnMapsViewModel

class GrenadesPracticeStrategyTickrate(
    override val appViewState: AppViewState,
    override val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>,
    private val mapName: String?,
    private val grenadeTypeName: String?
) : AppStateStrategy() {

    override fun applyStrategy(): AppViewState {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigation.GrenadesPractice.navId, false)
                .build(),
            args = Bundle().apply {
                putString(
                    PlacesOnMapsViewModel.GRENADE_PRACTICE_MAP_NAME,
                    mapName
                )
                putString(
                    PlacesOnMapsViewModel.GRENADE_PRACTICE_GRENADE_TYPE,
                    grenadeTypeName
                )
            }
        )
        return appViewState.copy(
            drawerGesturesEnabled = false,
            appTopBarState = AppViewState.AppTopBarState.AppTopBar(
                titleRes = AppViewState.StringResourceType.StringNative(
                    res = "$mapName $grenadeTypeName"
                ),
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
                                TargetNavigation.TickRate64(navParams)
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