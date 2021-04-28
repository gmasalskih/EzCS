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

class WeaponCharacteristicsSMGStrategy(
    override val appViewState: AppViewState,
    override val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>
) : AppStateStrategy() {

    override fun applyStrategy(): AppViewState {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigation.WeaponCharacteristics.navId, false)
                .build()
        )
        return appViewState.copy(
            appTopBarState = AppViewState.AppTopBarState.AppTopBar(
                titleRes = AppViewState.StringResourceType.StringIdRes(
                    R.string.app_top_bar_title_weapon_characteristics_smg
                ),
                appTopBarNavItem = AppViewState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = {
                        navigateTo(TargetNavigation.Back)
                    }
                ),
                appTopBarExtraItem = AppViewState.AppTopBarExtraItem(
                    icon = R.drawable.icon_vs,
                    isEnable = false,
                    onClick = {
                        // TODO: 17.04.2021 click VS Weapon goto screen VS (navigationTo TargetNavigation...)
                    }
                )
            ),
            appBottomBarState = AppViewState.AppBottomBarState.AppBottomBar(
                listOf(
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_weapon_characteristics_pistol,
                        icon = R.drawable.icon_pistol,
                        route = TargetNavigation.WeaponCharacteristicsPistol().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.WeaponCharacteristicsPistol(navParams)
                            )
                        }
                    ),
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_weapon_characteristics_heavy,
                        icon = R.drawable.icon_heavy,
                        route = TargetNavigation.WeaponCharacteristicsHeavy().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.WeaponCharacteristicsHeavy(navParams)
                            )
                        }
                    ),
                    AppViewState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_weapon_characteristics_smg,
                        icon = R.drawable.icon_smg,
                        route = TargetNavigation.WeaponCharacteristicsSMG().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.WeaponCharacteristicsSMG(navParams)
                            )
                        }
                    ),
                    AppViewState.AppBottomBarItem(
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