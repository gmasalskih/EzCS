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

class WeaponCharacteristicsStrategy(
    override val appState: AppState,
    override val navEventEmitter: FlowCollector<TargetNavigation>,
    override val cs:CoroutineScope
) : AppStateStrategy() {

    override fun applyStrategy(): AppState {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigation.MainMenu().navId, false)
                .build()
        )
        return appState.copy(
            drawerGesturesEnabled = false,
            appTopBarState = AppState.AppTopBarState.AppTopBar(
                titleRes = R.string.app_top_bar_title_weapon_characteristics,
                appTopBarNavItem = AppState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = {
                        navigateTo(TargetNavigation.Back)
                    }
                )
            ),
            appBottomBarState = AppState.AppBottomBarState.AppBottomBar(
                listOf(
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_weapon_characteristics_pistol,
                        icon = R.drawable.icon_pistol,
                        route = TargetNavigation.WeaponCharacteristicsPistol().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.WeaponCharacteristicsPistol(
                                    navParams
                                )
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_weapon_characteristics_heavy,
                        icon = R.drawable.icon_heavy,
                        route = TargetNavigation.WeaponCharacteristicsHeavy().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.WeaponCharacteristicsHeavy(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_weapon_characteristics_smg,
                        icon = R.drawable.icon_smg,
                        route = TargetNavigation.WeaponCharacteristicsSMG().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.WeaponCharacteristicsSMG(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_weapon_characteristics_rifle,
                        icon = R.drawable.icon_rifle,
                        route = TargetNavigation.WeaponCharacteristicsRifle().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.WeaponCharacteristicsRifle(navParams)
                            )
                        }
                    )
                )
            )
        )
    }
}