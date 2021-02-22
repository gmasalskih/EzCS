package ru.gmasalskikh.ezcs.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.NavEvent
import ru.gmasalskikh.ezcs.screens.grenades_practice.GrenadesPracticeView
import ru.gmasalskikh.ezcs.screens.map_callouts.MapCalloutsView
import ru.gmasalskikh.ezcs.screens.ranks.competitive.CompetitiveView
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.DangerZoneView
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.ProfileRankView
import ru.gmasalskikh.ezcs.screens.ranks.wingman.WingmanView
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsView

@Composable
fun ComposeNavigation(navigator: Navigator = get()) {
    val navController = AmbientNavController.current
    val appStateChangeEmitter = AmbientAppStateHolder.current.appStateChangeEmitter
    val cs = rememberCoroutineScope()
    DisposableEffect(key1 = null) {
        navigator.onAttach(navController)
        onDispose {
            navigator.onDetach()
        }
    }
    fun appStateChange(targetNavPath: TargetNavigationPath, bundle: Bundle? = null) = cs.launch {
        appStateChangeEmitter.emit(
            NavEvent(
                targetNavigationPath = targetNavPath,
                bundle = bundle
            )
        )
    }
    NavHost(
        navController = navController,
        startDestination = TargetNavigationPath.SPLASH_SCREEN.name
    ) {
        composable(TargetNavigationPath.SPLASH_SCREEN.name) {
            appStateChange(TargetNavigationPath.SPLASH_SCREEN)
            SplashScreenView(getViewModel()).Screen()
        }
        composable(TargetNavigationPath.PREVIEW.name) {
            appStateChange(TargetNavigationPath.PREVIEW)
            PreviewView(getViewModel()).Screen()
        }
        composable(TargetNavigationPath.MAIN_MENU.name) {
            appStateChange(TargetNavigationPath.MAIN_MENU)
            MainMenuView(getViewModel()).Screen()
        }
        composable(TargetNavigationPath.MAP_CALLOUTS.name) {
            appStateChange(TargetNavigationPath.MAP_CALLOUTS)
            MapCalloutsView(getViewModel()).Screen()
        }
        composable(TargetNavigationPath.WEAPON_CHARACTERISTICS.name) {
            appStateChange(TargetNavigationPath.WEAPON_CHARACTERISTICS)
            WeaponCharacteristicsView(getViewModel()).Screen()
        }
        composable(TargetNavigationPath.GRENADES_PRACTICE.name) {
            appStateChange(TargetNavigationPath.GRENADES_PRACTICE)
            GrenadesPracticeView(getViewModel()).Screen()
        }
        navigation(
            startDestination = TargetNavigationPath.RANKS_COMPETITIVE.name,
            route = TargetNavigationPath.RANKS.name
        ) {
            composable(TargetNavigationPath.RANKS_COMPETITIVE.name) {
                appStateChange(TargetNavigationPath.RANKS)
                appStateChange(TargetNavigationPath.RANKS_COMPETITIVE)
                CompetitiveView(getViewModel()).Screen()
            }
            composable(TargetNavigationPath.RANKS_WINGMAN.name) {
                appStateChange(TargetNavigationPath.RANKS_WINGMAN)
                WingmanView(getViewModel()).Screen()
            }
            composable(TargetNavigationPath.RANKS_DANGER_ZONE.name) {
                appStateChange(TargetNavigationPath.RANKS_DANGER_ZONE)
                DangerZoneView(getViewModel()).Screen()
            }
            composable(TargetNavigationPath.RANKS_PROFILE_RANK.name) {
                appStateChange(TargetNavigationPath.RANKS_PROFILE_RANK)
                ProfileRankView(getViewModel()).Screen()
            }
        }
    }
}