package ru.gmasalskikh.ezcs.navigation

enum class TargetNavigationPath {
    SPLASH_SCREEN,
    PREVIEW,
    MAIN_MENU,
    MAP_CALLOUTS,
    WEAPON_CHARACTERISTICS,
    GRENADES_PRACTICE,
    RANKS,
    RANKS_COMPETITIVE,
    RANKS_WINGMAN,
    RANKS_DANGER_ZONE,
    RANKS_PROFILE_RANK,
    BACK;

    val navId: Int
        get() = "android-app://androidx.navigation.compose/$name".hashCode()
}