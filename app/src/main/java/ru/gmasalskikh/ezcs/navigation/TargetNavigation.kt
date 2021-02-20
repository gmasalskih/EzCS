package ru.gmasalskikh.ezcs.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath.*

enum class TargetNavigationPath {
    SPLASH_SCREEN,
    PREVIEW,
    MAIN_MENU,
    RANKS,
    BACK;

    val navId: Int
        get() = "android-app://androidx.navigation.compose/$name".hashCode()
}

sealed class TargetNavigation(
    override val path: TargetNavigationPath,
    override val params: NavigationParams? = null
) : TargetNavigationContract {

    override val navId: Int
        get() = "android-app://androidx.navigation.compose/${path.name}".hashCode()

    object Back : TargetNavigation(BACK)

    object SplashScreen : TargetNavigation(SPLASH_SCREEN)

    data class Preview(
        override val params: NavigationParams
    ) : TargetNavigation(PREVIEW, params)

    data class MainMenu(
        override val params: NavigationParams
    ) : TargetNavigation(MAIN_MENU, params)

    object Ranks : TargetNavigation(RANKS)
}

private interface TargetNavigationContract {
    val path: TargetNavigationPath
    val params: NavigationParams?
    val navId: Int
}

data class NavigationParams(
    val args: Bundle? = null,
    val navOptions: NavOptions? = null,
    val navigatorExtras: Navigator.Extras? = null
)