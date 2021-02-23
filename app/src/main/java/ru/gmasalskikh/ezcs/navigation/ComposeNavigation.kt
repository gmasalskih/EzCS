package ru.gmasalskikh.ezcs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.*
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.screens.grenades_practice.GrenadesPracticeView
import ru.gmasalskikh.ezcs.screens.map_callouts.MapCalloutsView
import ru.gmasalskikh.ezcs.screens.ranks.competitive.CompetitiveView
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.DangerZoneView
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.ProfileRankView
import ru.gmasalskikh.ezcs.screens.ranks.wingman.WingmanView
import ru.gmasalskikh.ezcs.navigation.TargetNavigation.*

@Composable
fun ComposeNavigation(navigator: Navigator = get()) {
    val navController = AmbientNavController.current
    DisposableEffect(key1 = null) {
        navigator.onAttach(navController)
        onDispose {
            navigator.onDetach()
        }
    }
    NavHost(
        navController = navController,
        startDestination = SplashScreen.path
    ) {
        composable(SplashScreen.path) {
            SplashScreenView(getViewModel()).Screen()
        }
        composable(Preview().path) {
            PreviewView(getViewModel()).Screen()
        }
        composable(MainMenu().path) {
            MainMenuView(getViewModel()).Screen()
        }
        composable(MapCallouts.path) {
            MapCalloutsView(getViewModel()).Screen()
        }
//        navigation(
//            startDestination = WEAPON_CHARACTERISTICS_PISTOL.name,
//            route = WEAPON_CHARACTERISTICS.name,
//        ) {
//            composable(WEAPON_CHARACTERISTICS_PISTOL.name) {
//                WeaponCharacteristicsView(
//                    vm = getViewModel(),
//                    targetNavigationPath = WEAPON_CHARACTERISTICS_PISTOL
//                ).Screen()
//            }
//            composable(WEAPON_CHARACTERISTICS_HEAVY.name) {
//                WeaponCharacteristicsView(
//                    vm = getViewModel(),
//                    targetNavigationPath = WEAPON_CHARACTERISTICS_HEAVY
//                ).Screen()
//            }
//            composable(WEAPON_CHARACTERISTICS_SMG.name) {
//                WeaponCharacteristicsView(
//                    vm = getViewModel(),
//                    targetNavigationPath = WEAPON_CHARACTERISTICS_SMG
//                ).Screen()
//            }
//            composable(WEAPON_CHARACTERISTICS_RIFLE.name) {
//                WeaponCharacteristicsView(
//                    vm = getViewModel(),
//                    targetNavigationPath = WEAPON_CHARACTERISTICS_RIFLE
//                ).Screen()
//            }
//        }
        composable(GrenadesPractice.path) {
            GrenadesPracticeView(getViewModel()).Screen()
        }
        navigation(
            startDestination = RanksCompetitive().path,
            route = Ranks.path
        ) {
            composable(RanksCompetitive().path) {
                CompetitiveView(getViewModel()).Screen()
            }
            composable(RanksWingman().path) {
                WingmanView(getViewModel()).Screen()
            }
            composable(RanksDangerZone().path) {
                DangerZoneView(getViewModel()).Screen()
            }
            composable(RanksProfileRank().path) {
                ProfileRankView(getViewModel()).Screen()
            }
        }
    }
}