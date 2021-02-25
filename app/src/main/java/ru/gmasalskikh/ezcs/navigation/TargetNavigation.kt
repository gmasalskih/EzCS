package ru.gmasalskikh.ezcs.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath.*

sealed class TargetNavigation(
    val targetNavigationPath: TargetNavigationPath,
    override val params: NavigationParams? = null
) : TargetNavigationContract {

    override val path: String
        get() = targetNavigationPath.name

    override val navId: Int
        get() = "android-app://androidx.navigation.compose/$path".hashCode()

    object Back : TargetNavigation(BACK)

    object SplashScreen : TargetNavigation(SPLASH_SCREEN)

    data class Preview(
        override val params: NavigationParams? = null
    ) : TargetNavigation(PREVIEW)

    data class MainMenu(
        override val params: NavigationParams? = null
    ) : TargetNavigation(MAIN_MENU)

    object MapCallouts : TargetNavigation(MAP_CALLOUTS)

    object WeaponCharacteristics : TargetNavigation(WEAPON_CHARACTERISTICS)

    data class WeaponCharacteristicsPistol(
        override val params: NavigationParams? = null
    ) : TargetNavigation(WEAPON_CHARACTERISTICS_PISTOL)

    data class WeaponCharacteristicsHeavy(
        override val params: NavigationParams? = null
    ) : TargetNavigation(WEAPON_CHARACTERISTICS_HEAVY)

    data class WeaponCharacteristicsSMG(
        override val params: NavigationParams? = null
    ) : TargetNavigation(WEAPON_CHARACTERISTICS_SMG)

    data class WeaponCharacteristicsRifle(
        override val params: NavigationParams? = null
    ) : TargetNavigation(WEAPON_CHARACTERISTICS_RIFLE)

    object GrenadesPractice : TargetNavigation(GRENADES_PRACTICE)

    object Ranks : TargetNavigation(RANKS)

    data class RanksCompetitive(
        override val params: NavigationParams? = null
    ) : TargetNavigation(RANKS_COMPETITIVE)

    data class RanksWingman(
        override val params: NavigationParams? = null
    ) : TargetNavigation(RANKS_WINGMAN)

    data class RanksDangerZone(
        override val params: NavigationParams? = null
    ) : TargetNavigation(RANKS_DANGER_ZONE)

    data class RanksProfileRank(
        override val params: NavigationParams? = null
    ) : TargetNavigation(RANKS_PROFILE_RANK)

}

enum class TargetNavigationPath {
    SPLASH_SCREEN,
    PREVIEW,
    MAIN_MENU,
    MAP_CALLOUTS,
    WEAPON_CHARACTERISTICS,
    WEAPON_CHARACTERISTICS_PISTOL,
    WEAPON_CHARACTERISTICS_HEAVY,
    WEAPON_CHARACTERISTICS_SMG,
    WEAPON_CHARACTERISTICS_RIFLE,
    GRENADES_PRACTICE,
    RANKS,
    RANKS_COMPETITIVE,
    RANKS_WINGMAN,
    RANKS_DANGER_ZONE,
    RANKS_PROFILE_RANK,
    BACK;
}

private interface TargetNavigationContract {
    val path: String
    val navId: Int
    val params: NavigationParams?
}

data class NavigationParams(
    val args: Bundle? = null,
    val navOptions: NavOptions? = null,
    val navigatorExtras: Navigator.Extras? = null
)