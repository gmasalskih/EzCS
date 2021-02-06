package ru.gmasalskikh.ezcs.navigation

sealed class TargetNavigation(val path: String) {

    val navId: Int
        get() = "android-app://androidx.navigation.compose/$path".hashCode()

    object SplashScreen : TargetNavigation("SPLASH_SCREEN")
    object Preview : TargetNavigation("PREVIEW")
    object MainMenu : TargetNavigation("MAIN_MENU")
    object Ranks : TargetNavigation("RANKS")

}