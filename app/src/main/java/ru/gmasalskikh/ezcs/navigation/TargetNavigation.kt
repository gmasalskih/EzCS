package ru.gmasalskikh.ezcs.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

sealed class TargetNavigation(
    val path: String,
) : TargetNavigationContract {

    val navId: Int
        get() = "android-app://androidx.navigation.compose/$path".hashCode()

    data class SplashScreen(
        override val params: TargetNavigationParams? = null
    ) : TargetNavigation("SPLASH_SCREEN")

    data class Preview(
        override val params: TargetNavigationParams? = null
    ) : TargetNavigation("PREVIEW")

    data class MainMenu(
        override val params: TargetNavigationParams? = null
    ) : TargetNavigation("MAIN_MENU")

    data class Ranks(
        override val params: TargetNavigationParams? = null
    ) : TargetNavigation("RANKS")

}

private interface TargetNavigationContract {
    val params: TargetNavigationParams?
}

data class NavigationParams(
    override val args: Bundle? = null,
    override val navOptions: NavOptions? = null,
    override val navigatorExtras: Navigator.Extras? = null
) : TargetNavigationParams

interface TargetNavigationParams {
    val args: Bundle?
    val navOptions: NavOptions?
    val navigatorExtras: Navigator.Extras?
}