package ru.gmasalskikh.ezcs.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

sealed class TargetNavigation(
    val path: String,
    val args: Bundle? = null,
    val navOptions: NavOptions? = null,
    val navigatorExtras: Navigator.Extras? = null
) {
    val navId: Int
        get() = "android-app://androidx.navigation.compose/$path".hashCode()
    object SplashScreen : TargetNavigation("SPLASH_SCREEN")
    object Preview : TargetNavigation("PREVIEW")
    object MainMenu : TargetNavigation("MAIN_MENU")
    object Ranks : TargetNavigation("RANKS")

}