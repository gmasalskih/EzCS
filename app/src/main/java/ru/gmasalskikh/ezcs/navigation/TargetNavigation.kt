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
        override val params: NavigationParams? = null
    ) : TargetNavigation("SPLASH_SCREEN")

    data class Preview(
        override val params: NavigationParams? = null
    ) : TargetNavigation("PREVIEW")

    data class MainMenu(
        override val params: NavigationParams? = null
    ) : TargetNavigation("MAIN_MENU")

    data class Ranks(
        override val params: NavigationParams? = null
    ) : TargetNavigation("RANKS")

}

private interface TargetNavigationContract {
    val params: NavigationParams?
}

data class NavigationParams(
    val args: Bundle? = null,
    val navOptions: NavOptions? = null,
    val navigatorExtras: Navigator.Extras? = null
)