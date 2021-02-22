package ru.gmasalskikh.ezcs.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath.*

sealed class TargetNavigation(
    override val path: TargetNavigationPath,
    override val params: NavigationParams? = null
) : TargetNavigationContract {
    object SplashScreen : TargetNavigation(SPLASH_SCREEN)
    data class Preview(
        override val params: NavigationParams
    ) : TargetNavigation(PREVIEW, params)

    data class MainMenu(
        override val params: NavigationParams
    ) : TargetNavigation(MAIN_MENU, params)

    object MapCallouts : TargetNavigation(MAP_CALLOUTS)
    object WeaponCharacteristics : TargetNavigation(WEAPON_CHARACTERISTICS)
    object GrenadesPractice : TargetNavigation(GRENADES_PRACTICE)
    object Ranks : TargetNavigation(RANKS)
    data class RanksCompetitive(
        override val params: NavigationParams
    ) : TargetNavigation(RANKS_COMPETITIVE)

    data class RanksWingman(
        override val params: NavigationParams
    ) : TargetNavigation(RANKS_WINGMAN)

    data class RanksDangerZone(
        override val params: NavigationParams
    ) : TargetNavigation(RANKS_DANGER_ZONE)

    data class RanksProfileRank(
        override val params: NavigationParams
    ) : TargetNavigation(RANKS_PROFILE_RANK)

    object Back : TargetNavigation(BACK)
}

private interface TargetNavigationContract {
    val path: TargetNavigationPath
    val params: NavigationParams?
}

data class NavigationParams(
    val args: Bundle? = null,
    val navOptions: NavOptions? = null,
    val navigatorExtras: Navigator.Extras? = null
)