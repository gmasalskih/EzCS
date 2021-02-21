package ru.gmasalskikh.ezcs.navigation

enum class TargetNavigationPath {
    SPLASH_SCREEN,
    PREVIEW,
    MAIN_MENU,
    MAP_CALLOUTS,
    WEAPON_CHARACTERISTICS,
    GRENADES_PRACTICE,
    RANKS,
    BACK;

    val navId: Int
        get() = "android-app://androidx.navigation.compose/$name".hashCode()
}